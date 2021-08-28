package com.jancoyan.commentset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jancoyan.commentset.pojo.Collection;
import com.jancoyan.commentset.mapper.CollectionMapper;
import com.jancoyan.commentset.service.CollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.sql.Wrapper;

/**
 * @author Jancoyan
 * @since 2021-08-22
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {

    @Override
    public IPage<Collection> selectUserAll(String userId) {
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        IPage<Collection> iPage = new Page<>(1, 20);
        return baseMapper.selectPage(iPage, wrapper);
    }
}
