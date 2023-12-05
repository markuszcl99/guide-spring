package com.markus.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * @author: markus
 * @date: 2023/12/5 10:42 PM
 * @Description: 支持编码的资源示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class EncodeResourceDemo {
    public static void main(String[] args) throws IOException {
        String currentFilePath = System.getProperty("user.dir") + "/spring-resource/src/main/java/com/markus/spring/resource/EncodeResourceDemo.java";

        Resource resource = new FileSystemResource(currentFilePath);
        // 获取指定编码格式的Resource资源，如果编码方式不一样，可能会出现乱码
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
//        EncodedResource encodedResource = new EncodedResource(resource, "ISO8859-1");
        // 获取字符流
        try (Reader reader = encodedResource.getReader();) {
            String fileContent = IOUtils.toString(reader);
            System.out.println(fileContent);
        }
    }
}
