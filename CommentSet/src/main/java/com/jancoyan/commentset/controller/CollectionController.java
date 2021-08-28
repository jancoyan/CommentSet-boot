package com.jancoyan.commentset.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jancoyan.commentset.pojo.Collection;
import com.jancoyan.commentset.service.CollectionService;
import com.jancoyan.commentset.utils.Msg;
import org.eclipse.jdt.internal.compiler.env.IModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Jancoyan
 * @since 2021-08-22
 */
@Controller
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    CollectionService service;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Msg all(
            @RequestParam(value = "userId")String userId
    ){
        IPage<Collection> iPage = service.selectUserAll(userId);
        return Msg.success().add("pageInfo", iPage);
    }




}

