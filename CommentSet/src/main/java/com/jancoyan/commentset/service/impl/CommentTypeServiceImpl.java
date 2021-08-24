package com.jancoyan.commentset.service.impl;

import com.jancoyan.commentset.pojo.CommentType;
import com.jancoyan.commentset.mapper.CommentTypeMapper;
import com.jancoyan.commentset.service.CommentTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Jancoyan
 * @since 2021-08-24
 */
@Service
public class CommentTypeServiceImpl extends ServiceImpl<CommentTypeMapper, CommentType> implements CommentTypeService {

}
