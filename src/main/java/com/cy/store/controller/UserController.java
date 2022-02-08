package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){

        userService.reg(user);
        return new JsonResult<>(OK);
    }
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password , HttpSession session){
        User user = userService.login(username,password);
        session.setAttribute("uid",user.getUid());
        session.setAttribute("username",user.getUsername());
        System.out.println(getUidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(OK,user);
        }

//    @RequestMapping("reg")
//    public JsonResult<Void> reg(User user){
//        JsonResult<Void> reault = new JsonResult<>();
//        try {
//            userService.reg(user);
//            reault.setState(200);
//            reault.setMessage("用户创建成功");
//        } catch (UsernameDuplicateException e) {
//            e.printStackTrace();
//            reault.setState(4000);
//            reault.setMessage("用户名被占用");
//        }catch (InsertException e) {
//            e.printStackTrace();
//            reault.setState(5000);
//            reault.setMessage("注册时产生未知的异常");
//        }
//        return reault;
//    }
}
