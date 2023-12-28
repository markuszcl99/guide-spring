package com.markus.spring.conversion;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/28
 * @Description:
 */
public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {

  @Override
  public void registerCustomEditors(PropertyEditorRegistry registry) {

    registry.registerCustomEditor(User.class, new StringToPropertiesPropertyEditor());
  }
}
