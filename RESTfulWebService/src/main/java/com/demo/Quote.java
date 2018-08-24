package com.demo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Author:
 * @Description:
 * @Data:Created in 17:13 2018/8/23
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
    private String type;
    private Value value;

    public Quote() {
    }

    public Quote(String type, Value value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public Value getValue() {
        return value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
