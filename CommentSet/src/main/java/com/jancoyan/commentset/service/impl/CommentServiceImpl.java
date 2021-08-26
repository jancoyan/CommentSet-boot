package com.jancoyan.commentset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jancoyan.commentset.pojo.Comment;
import com.jancoyan.commentset.mapper.CommentMapper;
import com.jancoyan.commentset.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Jancoyan
 * @since 2021-08-22
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Override
    public IPage<Comment> selectIndexComment(Integer page, Integer limit, String search) {
        IPage<Comment> iPage = new Page<>(page, limit);
        // 查询条件构造器
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        // 筛选查询条件
        String[] split = search.split("&amp;");
        for (String item : split) {
            String[] split2 = item.split("=");
            if (split2.length < 2) {
                continue;
            }
            // 在这里写if else 的查询条件
        }
        wrapper.orderByDesc("post_date");
        return baseMapper.selectIndexComment(iPage, wrapper);
    }

    @Override
    public IPage<Comment> getCommentByType(String typeId, Integer page, Integer limit) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("comment_type", typeId);
        IPage<Comment> iPage = new Page<>(page, limit);
        wrapper.orderByDesc("post_date");
        return baseMapper.selectIndexComment(iPage, wrapper);
    }

    @Override
    public IPage<Comment> getUserComment(String userId, Integer page, Integer limit) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("comment_author_id", userId);
        IPage<Comment> iPage = new Page<>(page, limit);
        wrapper.orderByDesc("post_date");
        return baseMapper.selectPage(iPage, wrapper);
    }
}
