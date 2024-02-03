package com.markus.spring.transaction.service;

import com.markus.spring.data.jdbc.domain.entity.User;
import com.markus.spring.data.jdbc.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Objects;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/2
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public void processUserByProgram() {
        User user = new User();
        user.setName("markus zhang unique time :" + System.currentTimeMillis());
        user.setAge(25);
        user.setAddress("山东菏泽");
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    processUser();
                } catch (Exception e) {
                    // 如果发生异常，回滚事务
                    status.setRollbackOnly();
                    throw e;
                }
            }
        });
    }

    //    @Transactional
    public void processUser() {
        User user = new User();
        user.setName("markus zhang unique time :" + System.currentTimeMillis());
        user.setAge(25);
        user.setAddress("山东菏泽");

        // 1. 先向数据库中插入一条数据
        userDao.insertUser(user);

        // 故意抛出一个异常，验证下 第一步 的操作是否会回滚
        int i = 1 / 0;

        // 2. 再查询该数据
        User queryUserByName = userDao.queryUserByName(user.getName());
        if (Objects.isNull(queryUserByName)) {
            return;
        }
        // 3. 再更新该数据到数据库中
        queryUserByName.setAddress("北京朝阳");
        userDao.updateUser(queryUserByName);
    }

    public List<User> queryAllUsers() {
        List<User> users = userDao.queryUsers(-1);
        return users;
    }
}
