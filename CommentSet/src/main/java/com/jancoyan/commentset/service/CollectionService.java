package com.jancoyan.commentset.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jancoyan.commentset.pojo.Collection;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Jancoyan
 * @since 2021-08-22
 */
public interface CollectionService extends IService<Collection> {

    IPage<Collection> selectUserAll(String userId);
}
