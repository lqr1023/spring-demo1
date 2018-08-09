package com.demo;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author:
 * @Description:控制层代码 前台调用传参数，返回json结果文件
 * @Data:Created in 15:52 2018/8/8
 */

@RestController
public class GreetingController {
    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name",defaultValue = "World")String name){
        return new Greeting(counter.incrementAndGet(),String.format(template,name));
    }
    public static void main(String[] args) {
        AtomicLong c = new AtomicLong();
        System.out.println(c.incrementAndGet());
        System.out.println(c.incrementAndGet());
    }
}
