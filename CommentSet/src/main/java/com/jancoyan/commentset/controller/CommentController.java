package com.jancoyan.commentset.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jancoyan.commentset.pojo.Comment;
import com.jancoyan.commentset.service.CommentService;
import com.jancoyan.commentset.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Jancoyan
 * @since 2021-08-22
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService service;

    @RequestMapping(value = "/all")
    public Msg getAll(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*");
        IPage<Comment> iPage = new Page<>(page, limit);
        iPage = service.page(iPage, null);
        return Msg.success().add("pageInfo", iPage);
    }

}

