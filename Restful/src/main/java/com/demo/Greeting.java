package com.demo;

/**
 * @Author:
 * @Description:
 * @Data:Created in 15:44 2018/8/8
 */
public class Greeting {
    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
