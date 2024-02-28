package com.markus.spring.transaction.service;

import com.markus.spring.data.jdbc.domain.entity.User;
import com.markus.spring.data.jdbc.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.markus.spring.data.jdbc.domain.entity.User.createUser;

/**
 * @author: markus
 * @date: 2024/2/4 10:46 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class BaseXmlUserService {

    private UserDao userDao;

    @Transactional
    public void processUser() {
        User user = createUser();

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

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
