package com.markus.spring.conversion;

import java.beans.PropertyEditor;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/27
 * @Description: {@link PropertyEditor} 示例
 */
public class PropertyEditorDemo {
  public static void main(String[] args) {
    PropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
    propertyEditor.setAsText("name = markus");

    Object value = propertyEditor.getValue();
    System.out.println(value);

    String asText = propertyEditor.getAsText();
    System.out.println(asText);
  }
}
