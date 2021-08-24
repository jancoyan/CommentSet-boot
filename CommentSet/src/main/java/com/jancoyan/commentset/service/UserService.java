package com.jancoyan.commentset.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jancoyan.commentset.pojo.Type;
import com.jancoyan.commentset.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Jancoyan
 * @since 2021-08-22
 */
public interface UserService extends IService<User> {

    IPage<User> getAll(Integer page, Integer limit);
}
