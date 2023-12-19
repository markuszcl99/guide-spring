package com.markus.spring.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/19
 * @Description:
 */
public class ResourceBundleDemo {
  public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException {
    System.out.println("中文: ");
    display("META-INF/messages", Locale.CHINA);
    System.out.println("英文: ");
    display("META-INF/messages", Locale.ENGLISH);
  }

  private static void display(String basename, Locale locale) throws IOException, IllegalAccessException, InstantiationException {
    ResourceBundle bundle = new UTF8Control()
        .newBundle(
            basename,
            locale,
            "java.properties",
            ResourceBundleDemo.class.getClassLoader(),
            false);

    String name = bundle.getString("name");
    System.out.println(name);
    String hello = bundle.getString("hello");
    System.out.println(hello);
  }


  private static class UTF8Control extends ResourceBundle.Control {
    @Override
    public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException {
      String bundleName = toBundleName(baseName, locale);
      String resourceName = toResourceName(bundleName, "properties");
      ResourceBundle bundle = null;
      // 读取 资源文件
      InputStream stream = loader.getResourceAsStream(resourceName);
      if (stream != null) {
        try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
          bundle = new PropertyResourceBundle(reader);
        }
      }
      return bundle;
    }
  }
}
