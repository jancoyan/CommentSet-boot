package com.jancoyan.commentset.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jancoyan.commentset.pojo.Type;
import com.jancoyan.commentset.service.TypeService;
import com.jancoyan.commentset.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jancoyan
 * @since 2021-08-24
 */
@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    TypeService service;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Msg getAll(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ){
        IPage<Type> iPage = service.getAll(page, limit);
        return Msg.success().add("pageInfo", iPage);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Msg batchDelete(
            @RequestParam(value = "ids") String ids
    ){
        String[] splits = ids.split("&");

        Type type = new Type();

        for (String split : splits){
            if(!"".equals(split)){
                type.setTypeId(Integer.parseInt(ids));
                type.deleteById();
            }else break;
        }


        return Msg.success();
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Msg updateType(
            @RequestParam(value = "typeId") String typeId,
            @RequestParam(value = "typeName") String typeName,
            @RequestParam(value = "description") String description
    ){
        Type type = new Type();
        type.setTypeId(Integer.parseInt(typeId));
        type.setTypeName(typeName);
        type.setTypeDescription(description);
        type.updateById();
        return Msg.success();
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public Msg addType(
            @RequestParam(value = "typeName") String typeName,
            @RequestParam(value = "typeDescription") String typeDescription
    ){
        Type type = new Type();
        type.setTypeName(typeName);
        type.setTypeDescription(typeDescription);
        type.insert();
        return Msg.success();
    }
}
