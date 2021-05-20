package com.maben.druid_monitor.pojo.base;

import lombok.Data;

@Data
public class Result<T> {
    /**
     * 返回码 1：成功，0：失败
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 返回结果
     */
    private T t;
}
