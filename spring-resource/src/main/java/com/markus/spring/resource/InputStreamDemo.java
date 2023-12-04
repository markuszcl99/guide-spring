package com.markus.spring.resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/4
 * @Description: 输入流示例
 */
public class InputStreamDemo {
  public static void main(String[] args) {
    try (InputStream is = InputStreamDemo.class.getClassLoader().getResourceAsStream("resource.txt")) {
      byte[] buffer = new byte[1024];
      int length;
      while ((length = is.read(buffer)) != -1) {
        String content = new String(buffer, 0, length, StandardCharsets.UTF_8);
        System.out.print(content);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
