package com.jancoyan.commentset.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jancoyan.commentset.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Jancoyan
 * @since 2021-08-22
 */
public interface CommentService extends IService<Comment> {
    IPage<Comment> selectIndexComment(Integer page, Integer limit, String search);

    IPage<Comment> getCommentByType(String typeId, Integer page, Integer limit);

    IPage<Comment> getUserComment(String userId, Integer page, Integer limit);
}
