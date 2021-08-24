package com.jancoyan.commentset.service.impl;

import com.jancoyan.commentset.pojo.Type;
import com.jancoyan.commentset.mapper.TypeMapper;
import com.jancoyan.commentset.service.TypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Jancoyan
 * @since 2021-08-24
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

}
