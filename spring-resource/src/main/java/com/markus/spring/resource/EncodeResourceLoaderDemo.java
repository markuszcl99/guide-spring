package com.markus.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * @author: markus
 * @date: 2023/12/5 10:42 PM
 * @Description: 通过资源加载器获取资源进行控制台打印的示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class EncodeResourceLoaderDemo {
    public static void main(String[] args) throws IOException {
        String currentFilePath = "/" + System.getProperty("user.dir") + "/spring-resource/src/main/java/com/markus/spring/resource/EncodeResourceLoaderDemo.java";

        ResourceLoader resourceLoader = new FileSystemResourceLoader();
        Resource resource = resourceLoader.getResource(currentFilePath);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        try (Reader reader = encodedResource.getReader();) {
            String fileContent = IOUtils.toString(reader);
            System.out.println(fileContent);
        }

    }
}
