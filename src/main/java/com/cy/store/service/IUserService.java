package com.cy.store.service;

import com.cy.store.entity.User;

/**
 * 用户模块有业务层接口
 */
public interface IUserService {

    /**
     * 用户注册
     * @param user
     */
    void reg(User user);

    /**
     *
     * @param username
     * @param password
     * @return
     */
    User login(String username,String password);
}
