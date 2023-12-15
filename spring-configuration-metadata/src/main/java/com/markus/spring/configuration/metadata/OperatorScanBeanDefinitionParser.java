package com.markus.spring.configuration.metadata;

import com.markus.spring.configuration.annotation.Operator;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.*;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author: markus
 * @date: 2023/12/15 11:04 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class OperatorScanBeanDefinitionParser implements BeanDefinitionParser {

    private BeanNameGenerator beanNameGenerator = new DefaultBeanNameGenerator();

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        // 找到 该标签的属性
        String basePackage = element.getAttribute("base-package");
        List<String> classFileList = scanPackage(basePackage);
        List<Class<?>> candidateOperatorBean = getCandidateOperatorBean(classFileList);

        BeanDefinitionRegistry registry = parserContext.getRegistry();
        // 注册 BeanDefinition
        registerOperator(candidateOperatorBean, registry);
        return null;
    }

    private void registerOperator(List<Class<?>> candidateOperatorBean, BeanDefinitionRegistry registry) {
        for (Class<?> candidateOperator : candidateOperatorBean) {
            BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(candidateOperator).getBeanDefinition();
            Operator operator = candidateOperator.getAnnotation(Operator.class);
            String beanName = operator.value();
            if (!StringUtils.hasText(beanName)) {
                beanName = beanNameGenerator.generateBeanName(beanDefinition, registry);
            }
            registry.registerBeanDefinition(beanName, beanDefinition);
        }
    }

    private List<Class<?>> getCandidateOperatorBean(List<String> classNameFile) {
        List<Class<?>> result = new ArrayList<>(classNameFile.size());

        for (String className : classNameFile) {
            try {
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(Operator.class)) {
                    result.add(clazz);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("class load error! its " + className);
            }
        }
        return result;
    }


    private List<String> scanPackages(List<String> packageNames) {
        List<String> tempBeanNames = new ArrayList<>();
        for (String packageName : packageNames) {
            tempBeanNames.addAll(scanPackage(packageName));
        }
        return tempBeanNames;
    }

    private List<String> scanPackage(String packageName) {
        List<String> tempBeanNames = new ArrayList<>();
        URI uri = null;
        try {
            uri = this.getClass().getResource("/" + packageName.replaceAll("\\.", "/")).toURI();
        } catch (URISyntaxException e) {
            System.err.println("加载包路径出现错误");
        }
        assert uri != null;
        File dir = new File(uri);
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                tempBeanNames.addAll(scanPackage(packageName + "." + file.getName()));
            } else {
                String controllerName = packageName + "." + file.getName().replace(".class", "");
                tempBeanNames.add(controllerName);
            }
        }
        return tempBeanNames;
    }
}
