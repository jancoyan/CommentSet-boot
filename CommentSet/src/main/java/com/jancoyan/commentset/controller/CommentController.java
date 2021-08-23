package com.jancoyan.commentset.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jancoyan.commentset.pojo.Comment;
import com.jancoyan.commentset.service.CommentService;
import com.jancoyan.commentset.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

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
    public Msg getIndexComment(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(name = "search", defaultValue = "") String search){
        IPage<Comment> iPage = service.selectIndexComment(page, limit, search);
        return Msg.success().add("pageInfo", iPage);
    }

    @RequestMapping(value = "/post")
    public Msg submitComment(
            @RequestParam(name = "content") String content){
        System.out.println(content);
        // 组装
        Comment comment = new Comment();
        comment.setCommentContent(content);
        comment.setPostDate(new Date());
        // 注册功能有了之后再填充
        comment.setCommentAuthorId(10000);
        // 直接插入
        boolean suc = comment.insert();
        // 插入结果
        if (suc){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

}

