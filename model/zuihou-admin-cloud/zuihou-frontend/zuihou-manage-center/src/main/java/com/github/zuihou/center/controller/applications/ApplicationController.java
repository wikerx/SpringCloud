package com.github.zuihou.center.controller.applications;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zuihou
 * @createTime 2018-03-13 18:50
 */
@Controller
@RequestMapping("/application")
public class ApplicationController {


    @GetMapping("/index")
    public String index() {
        return "developer/application/index";
    }

}
