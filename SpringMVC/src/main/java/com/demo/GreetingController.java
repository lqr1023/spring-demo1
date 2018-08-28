package com.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author:
 * @Description:
 * @Data:Created in 11:00 2018/8/28
 */

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name",required = false,defaultValue = "World")String name, Model model){
        model.addAttribute("name",name);
        return "greeting";
    }
}
