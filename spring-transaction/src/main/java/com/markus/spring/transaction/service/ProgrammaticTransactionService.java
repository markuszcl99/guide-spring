package com.markus.spring.transaction.service;

import com.markus.spring.data.jdbc.domain.entity.User;
import com.markus.spring.data.jdbc.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import static com.markus.spring.data.jdbc.domain.entity.User.createUser;

/**
 * @author: markus
 * @date: 2024/2/4 12:17 AM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Service
public class ProgrammaticTransactionService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public void method() {
        User user = createUser();
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    userDao.insertUser(user);
                } catch (Exception e) {
                    // 捕获异常，并将事务回滚
                    status.setRollbackOnly();
                    // 并将异常跑出去
                    throw e;
                }
            }
        });
    }
}
