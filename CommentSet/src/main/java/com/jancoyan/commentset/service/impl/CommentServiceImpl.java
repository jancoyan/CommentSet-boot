package com.jancoyan.commentset.service.impl;

import com.jancoyan.commentset.pojo.Comment;
import com.jancoyan.commentset.mapper.CommentMapper;
import com.jancoyan.commentset.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jancoyan
 * @since 2021-08-22
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
