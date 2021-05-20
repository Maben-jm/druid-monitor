package com.maben.druid_monitor.pojo.base;

public enum  Code {
    SUCCESS(1,"成功"),FAIL(0,"失败");

    private final int code;
    private final String msg;
    private Code(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
