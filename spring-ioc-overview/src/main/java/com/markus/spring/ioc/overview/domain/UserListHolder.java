package com.markus.spring.ioc.overview.domain;

import java.util.Collection;

/**
 * @author: markus
 * @date: 2023/12/8 10:50 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class UserListHolder {

    private Collection<User> users;

    public UserListHolder() {

    }

    public UserListHolder(Collection<User> users) {
        this.users = users;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserListHolder{" +
                "users=" + users +
                '}';
    }
}
