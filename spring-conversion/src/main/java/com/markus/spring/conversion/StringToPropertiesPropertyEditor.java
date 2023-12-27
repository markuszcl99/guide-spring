package com.markus.spring.conversion;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/27
 * @Description:
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport {

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    Properties properties = new Properties();
    try {
      properties.load(new StringReader(text));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
    setValue(properties);
  }

  @Override
  public String getAsText() {
    Properties properties = (Properties) getValue();
    StringBuilder sb = new StringBuilder();
    properties.forEach((key, value) -> sb.append(key).append("=").append(value).append("\n"));
    return sb.toString();
  }
}
