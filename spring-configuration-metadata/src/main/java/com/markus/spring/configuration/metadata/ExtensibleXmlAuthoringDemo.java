package com.markus.spring.configuration.metadata;

import com.markus.spring.configuration.operator.TableParser;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: markus
 * @date: 2023/12/15 11:32 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ExtensibleXmlAuthoringDemo {

    /**
     * 基于 Extensible XML authoring 扩展 Spring XML 元素知识点总结：
     * 1. 编写 xsd 文件 参考 com/markus/spring/configuration/metadata/markus.xsd
     * 2. 编写 NamespaceHandler ，参考 MarkusNamespaceHandler & OperatorScanBeanDefinitionParser 这些类是与xsd文件对应的，这里就是自定义扫描 Bean的工具
     * 3. 在类路径下添加 META-INF/spring.handlers 文件 目的是将 xsd 命名空间 与 NamespaceHandler 建立映射关系
     * 4. 在类路径下添加 META-INF/spring.schemas 将 xsd 命名空间和实际的 xsd 文件建立映射关系
     * 5. 编写示例
     *
     * @param args
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/markus-ioc-context.xml");

        TableParser tableParser = context.getBean("tableParser", TableParser.class);
        System.out.println(tableParser);

        context.close();
    }
}
