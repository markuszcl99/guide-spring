package com.markus.spring.i18n;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/21
 * @Description: 基于 Java NIO 2 实现动态刷新的消息源
 * 实现原理:
 * 1. 定位资源位置（properties文件）
 * 2. 初始化 Properties 对象
 * 3. 实现 AbstractMessageSource#resolveCode 方法
 * 4. 监听资源文件（Java NIO 2 WatchService）
 * 5. 使用线程池处理文件变化
 * 6. 重新加载 Properties 对象
 */
public class DynamicRefreshMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

  private static final String relativePath = "msg.properties";
  private static final String resourceFilePath = "META-INF/" + relativePath;

  private static final String DEFAULT_ENCODING = "UTF-8";

  private final Resource messagesPropertiesResource;
  private final Properties messagesProperties;
  private final ExecutorService executorService;

  private ResourceLoader resourceLoader;


  public DynamicRefreshMessageSource() {
    this.messagesPropertiesResource = getMessagesPropertiesResource();
    this.messagesProperties = loadMessagesProperties();
    this.executorService = Executors.newSingleThreadExecutor();
    // 处理 消息源 变更
    onMessagesPropertiesChanged();
  }

  private void onMessagesPropertiesChanged() {
    // 只处理文件
    if (this.messagesPropertiesResource.isFile()) {
      try {
        // 1. 获取对应文件系统的文件
        File file = this.messagesPropertiesResource.getFile();
        Path path = file.toPath();
        // 2. 获取当前文件系统
        FileSystem fileSystem = FileSystems.getDefault();
        // 3. 新建一个 WatchService 用于 文件事件 监听注册
        WatchService watchService = fileSystem.newWatchService();
        // 4. 获取当前文件的父目录
        Path parentDir = path.getParent();
        // 5. 将 WatchService 注册到父目录上，监听 该目录的修改事件
        parentDir.register(watchService, ENTRY_MODIFY);
        // 6. 处理事件变更（异步，不阻塞主线程）
        processMessagesChangedEvent(watchService);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

    }
  }

  private void processMessagesChangedEvent(WatchService watchService) {
    this.executorService.execute(() -> {
      // 设置一个死循环
      while (true) {
        WatchKey watchKey = null;
        try {
          watchKey = watchService.take();
          // 阻塞等待一个 watch key
          // 处理 watch key 有效的情况
          if (watchKey.isValid()) {
            for (WatchEvent<?> event : watchKey.pollEvents()) {
              // 监听的目录
              Path dirPath = (Path) watchKey.watchable();
              // 监听到的文件路径
              Path filePath = (Path) event.context();
              // 事件发生源是 相对路径（我们这里只处理 目标文件）
              if (relativePath.equals(filePath.getFileName().toString())) {
                // 得到绝对路径
                Path resolve = dirPath.resolve(filePath);
                File file = resolve.toFile();
                Properties properties = loadMessagesProperties(new FileReader(file));
                synchronized (this.messagesProperties) {
                  this.messagesProperties.clear();
                  this.messagesProperties.putAll(properties);
                }
              }
            }
          }
        } catch (InterruptedException | IOException e) {
          throw new RuntimeException(e);
        } finally {
          if (watchKey != null) {
            watchKey.reset();
          }
        }
      }
    });
  }

  private Properties loadMessagesProperties() {
    EncodedResource encodedResource = new EncodedResource(this.messagesPropertiesResource, DEFAULT_ENCODING);
    try (Reader reader = encodedResource.getReader()) {
      return loadMessagesProperties(reader);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private Properties loadMessagesProperties(Reader reader) {
    Properties properties = new Properties();
    try {
      properties.load(reader);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return properties;
  }

  private Resource getMessagesPropertiesResource() {
    return getResourceLoader().getResource(resourceFilePath);
  }

  @Override
  protected MessageFormat resolveCode(String code, Locale locale) {
    String messageFormatPattern = this.messagesProperties.getProperty(code);
    if (StringUtils.hasText(messageFormatPattern)) {
      return new MessageFormat(messageFormatPattern, locale);
    }
    return null;
  }

  public ResourceLoader getResourceLoader() {
    return this.resourceLoader != null ? this.resourceLoader : new DefaultResourceLoader();
  }

  @Override
  public void setResourceLoader(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  public static void main(String[] args) throws InterruptedException {
    DynamicRefreshMessageSource messageSource = new DynamicRefreshMessageSource();

    for (int i = 0; i < 10000; i++) {
      String hello = messageSource.getMessage("hello", null, Locale.CHINA);
      System.out.println(hello);
      Thread.sleep(1000);
    }
  }
}
