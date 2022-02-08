package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * 用户模块业务层的实现类
 * 将当前类的对象交给spring来管理，自动创建对象
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        String username = user.getUsername();
        User result = userMapper.findByUsername(username);

        if (result != null){
            throw new UsernameDuplicateException("用户名被占用");
        }
        String password = user.getPassword();
        //密码加密
        String salt = UUID.randomUUID().toString().toUpperCase(Locale.ROOT);
        user.setSalt(salt);
        String md5password = getMD5Password(password,salt);

        user.setPassword(md5password);

        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date data = new Date();
        user.setCreatedTime(data);
        user.setModifiedTime(data);

        //执行注册业务功能的实现
        Integer rows = userMapper.insert(user);
        if (rows != 1){
            throw  new InsertException("用户注册异常");
        }
    }

    @Override
    public User login(String username, String password) {
        User result =  userMapper.findByUsername(username);
        //判断是否被删除
        if (result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
        if (result == null){
            throw new UserNotFoundException("用户名不存在");
        }

        String  as = result.getSalt();
        String md5password = getMD5Password(password,as);
        String passwords = result.getPassword();
        if (!md5password.equals(passwords)){
            throw new PasswordNotMatchException("密码错误");
        }

        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    /**
     * 定义一个md5算法的加密
     */
    private String getMD5Password (String password,String  salt){
        for(int i =0;i<3;i++){
           password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }

        return password;
    }
}
