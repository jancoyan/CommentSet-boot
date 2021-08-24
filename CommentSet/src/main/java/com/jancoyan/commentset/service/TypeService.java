package com.jancoyan.commentset.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jancoyan.commentset.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Jancoyan
 * @since 2021-08-24
 */
public interface TypeService extends IService<Type> {

    IPage<Type> getAll(Integer page, Integer limit);
}
