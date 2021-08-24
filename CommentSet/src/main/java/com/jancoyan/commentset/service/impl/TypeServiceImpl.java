package com.jancoyan.commentset.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jancoyan.commentset.pojo.Type;
import com.jancoyan.commentset.mapper.TypeMapper;
import com.jancoyan.commentset.service.TypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jancoyan
 * @since 2021-08-24
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {


    @Override
    public IPage<Type> getAll(Integer page, Integer limit) {
        IPage<Type> iPage = new Page<>(page, limit);
        return baseMapper.selectPage(iPage, null);
    }


}
