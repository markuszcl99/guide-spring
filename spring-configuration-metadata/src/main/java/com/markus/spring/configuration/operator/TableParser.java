package com.markus.spring.configuration.operator;

import com.markus.spring.configuration.annotation.Operator;
import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: markus
 * @date: 2023/12/15 11:33 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Operator(value = "tableParser")
public class TableParser {

    @Autowired
    private User user;

    @Override
    public String toString() {
        return "TableParser{" +
                "user=" + user +
                '}';
    }
}
