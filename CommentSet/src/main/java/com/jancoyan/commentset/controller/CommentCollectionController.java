package com.jancoyan.commentset.controller;


import com.jancoyan.commentset.service.CommentCollectionService;
import com.jancoyan.commentset.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Jancoyan
 * @since 2021-08-22
 */
@Controller
@RequestMapping("/collection_comment")
public class CommentCollectionController {

    @Autowired
    CommentCollectionService service;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Msg



}

