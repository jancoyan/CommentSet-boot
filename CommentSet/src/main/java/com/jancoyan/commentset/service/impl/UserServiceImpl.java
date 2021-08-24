package com.jancoyan.commentset.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jancoyan.commentset.pojo.Type;
import com.jancoyan.commentset.pojo.User;
import com.jancoyan.commentset.mapper.UserMapper;
import com.jancoyan.commentset.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Jancoyan
 * @since 2021-08-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public IPage<User> getAll(Integer page, Integer limit) {
        IPage<User> iPage = new Page<>(page, limit);
        return baseMapper.selectPage(iPage, null);
    }
}
