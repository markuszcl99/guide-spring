package com.markus.spring.resource.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/6
 * @Description:
 */
public interface ResourceUtils {
  static String getContent(Resource resource) {
    try {
      return getContent(resource, "UTF-8");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  static String getContent(Resource resource, String encoding) throws IOException {
    EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
    try (Reader reader = encodedResource.getReader()) {
      return IOUtils.toString(reader);
    }
  }
}
