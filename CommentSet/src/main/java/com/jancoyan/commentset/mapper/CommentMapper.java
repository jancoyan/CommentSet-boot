package com.jancoyan.commentset.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jancoyan.commentset.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author Jancoyan
 * @since 2021-08-22
 */
public interface CommentMapper extends BaseMapper<Comment> {

    IPage<Comment> selectIndexComment(IPage<Comment> iPage,  Wrapper ew);


}
