package com.markus.spring.bean.definition;

import com.markus.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/5
 * @Description: Bean的垃圾回收
 */
public class BeanGarbageCollectionDemo {
  public static void main(String[] args) throws InterruptedException {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    applicationContext.register(BeanInitializationAndDestroyDemo.class);
    applicationContext.refresh();

    Thread.sleep(5000);
    applicationContext.close();
    // 手动触发Java垃圾回收机制，释放不再使用的对象内存空间。
    System.gc();
    Thread.sleep(5000);
  }
}
