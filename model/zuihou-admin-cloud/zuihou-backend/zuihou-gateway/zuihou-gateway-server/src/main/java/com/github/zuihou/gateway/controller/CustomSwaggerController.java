package com.github.zuihou.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomSwaggerController {
    @RequestMapping("doc")
    public String swagger(String url, ModelMap model) {
        model.put("url", url);
        return "swagger-ui";
    }
}
