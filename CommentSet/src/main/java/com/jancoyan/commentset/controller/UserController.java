package com.jancoyan.commentset.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jancoyan.commentset.pojo.User;
import com.jancoyan.commentset.service.UserService;
import com.jancoyan.commentset.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author Jancoyan
 * @since 2021-08-22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Msg login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            HttpSession session
    ){
        // 使用ActiveRecord进行查找
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        wrapper.eq("user_pwd", password);
        User user = new User();
        user = user.selectOne(wrapper);
        // 登录成功之后应该有用户的session
        if (null != user){
            session.setAttribute("user", user);
        }
        return Msg.success().add("user", user);
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public Msg logout(
            String userId,
            HttpSession session){
        session.removeAttribute("user");
        return Msg.success().add("rst", true);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Msg getAll(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ){
        IPage<User> iPage = service.getAll(page, limit);
        return Msg.success().add("pageInfo", iPage);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public  Msg register(
            @RequestParam(value = "username")String username,
            @RequestParam(value = "password")String password,
            HttpServletRequest request
    ){
        User user = new User();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        int count = user.selectCount(wrapper);
        if(count != 0){
            return Msg.fail().add("msg", "用户名已经被注册了");
        }
        user.setUserName(username);
        user.setCreateDate(new Date());
        user.setUserPwd(password);
        user.setUserIp(request.getRemoteAddr());
        user.insert();
        return Msg.success().add("user", user);
    }
}

