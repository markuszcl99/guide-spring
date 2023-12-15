package com.markus.spring.configuration.factory;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/15
 * @Description:
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {
  @Override
  public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
    YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
    yamlPropertiesFactoryBean.setResources(resource.getResource());
    Properties properties = yamlPropertiesFactoryBean.getObject();
    if (properties == null) {
      properties = new Properties();
    }
    return new PropertiesPropertySource(name, properties);
  }
}
