package com.jancoyan.commentset.service.impl;

import com.jancoyan.commentset.pojo.User;
import com.jancoyan.commentset.mapper.UserMapper;
import com.jancoyan.commentset.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户名 服务实现类
 * </p>
 *
 * @author Jancoyan
 * @since 2021-08-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
