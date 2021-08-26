package com.jancoyan.commentset.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jancoyan.commentset.pojo.Comment;
import com.jancoyan.commentset.service.CommentService;
import com.jancoyan.commentset.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.event.InternalFrameAdapter;
import java.io.UnsupportedEncodingException;
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
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(value = "search", defaultValue = "") String search){
        IPage<Comment> iPage = service.selectIndexComment(page, limit, search);
        return Msg.success().add("pageInfo", iPage);
    }

    @RequestMapping(value = "/post")
    public Msg submitComment(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "typeId") String typeId,
            HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        System.out.println(content);
        // 组装
        Comment comment = new Comment();
        comment.setCommentContent(content);
        comment.setPostDate(new Date());
        comment.setCommentAuthorId(Integer.parseInt(userId));
        comment.setCommentType(Integer.parseInt(typeId));
        // 直接插入
        boolean suc = comment.insert();
        // 插入结果
        if (suc){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Msg getUserComment(
            @RequestParam(value = "userId")String userId,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit
    ){
        IPage<Comment> iPage = service.getUserComment(userId, page, limit);
        return Msg.success().add("pageInfo", iPage);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Msg batchdelete(String ids){
        String[] splits = ids.split("&");

        Comment comment = new Comment();

        for(String split: splits){
            if (!"".equals(split)){
                comment.setCommentId(Integer.parseInt(split));
                comment.deleteById();
            } else break;
        }
        return Msg.success();
    }

    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public Msg getCommentByType(
            @RequestParam(value = "typeId")String typeId,
            @RequestParam(value = "page")Integer page,
            @RequestParam(value = "limit")Integer limit
    ){
        IPage<Comment> iPage = service.getCommentByType(typeId, page, limit);
        return Msg.success().add("pageInfo", iPage);
    }

}

