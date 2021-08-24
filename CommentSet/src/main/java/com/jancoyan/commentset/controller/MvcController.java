/**
 * @Author: Yan Jingcun
 * @Date: 2021/6/29
 * @Description:
 * @Version: 1.0
 */

package com.jancoyan.commentset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MvcController {

    @GetMapping("/workbench/{file}")
    public String workbenchResource(@PathVariable(value = "file") String file) {
        return "/workbench/" + file;
    }

    @GetMapping("/workbench/{file1}/{file2}")
    public String workbenchResource(
            @PathVariable(value = "file1") String file1,
            @PathVariable(value = "file2") String file2) {
        return "/workbench/" + file1 + "/" + file2;
    }
}
