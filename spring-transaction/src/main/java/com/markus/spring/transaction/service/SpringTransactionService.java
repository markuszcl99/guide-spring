package com.markus.spring.transaction.service;

import com.markus.spring.data.jdbc.domain.entity.User;
import com.markus.spring.data.jdbc.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: markus
 * @date: 2024/2/3 10:19 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Service
public class SpringTransactionService {

    @Autowired
    private UserDao userDao;

    // 事务传播行为 默认 REQUIRED
    @Transactional(rollbackFor = IllegalArgumentException.class, noRollbackFor = IllegalStateException.class)
    public void method() {
        User user = new User();
        long currentTime = System.currentTimeMillis();
        System.out.println("currentTime : " + currentTime);
        user.setName("帅气的小张 " + currentTime);
        user.setAge(25);
        user.setAddress("山东菏泽");
        // 向数据库中插入一条数据
        userDao.insertUser(user);

        // 抛出该异常会回滚
//        throw new IllegalArgumentException("违规参数");

        // 抛出该异常不会回滚
        throw new IllegalStateException("违规状态");
    }

    @Transactional(readOnly = true)
    public List<User> queryUsers() {
        return userDao.queryUsers(0);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void methodA() {
        System.out.println("methodA invoke... ");
    }


}
