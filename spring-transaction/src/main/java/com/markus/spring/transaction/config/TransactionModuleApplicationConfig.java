package com.markus.spring.transaction.config;

import com.markus.spring.data.jdbc.config.JdbcApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/2
 * @Description:
 */
@Configuration
@EnableTransactionManagement
@Import(JdbcApplicationConfiguration.class)
@ComponentScan({
    "com.markus.spring.transaction.service"
})
public class TransactionModuleApplicationConfig {

  @Bean
  public TransactionManager transactionManager(DataSource dataSource) {
    return new JdbcTransactionManager(dataSource);
  }

}
