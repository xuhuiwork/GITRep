package com.cy.store.controller;

import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 表示控制层类的基类
 */
public class BaseController {

    /** 操作成功的状态码 */
    public static final int OK = 200;

    public void test(){

    }

    /** @ExceptionHandler用于统一处理方法抛出的异常 */
    @ExceptionHandler({ServiceException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<Void>(e);
        if (e instanceof UsernameDuplicateException) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
            result.setMessage("用户名没有找到");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
            result.setMessage("密码错误");
        }else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        }
        return result;
    }

    protected final Integer getUidFromSession(HttpSession session){
        return Integer.valueOf( session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
