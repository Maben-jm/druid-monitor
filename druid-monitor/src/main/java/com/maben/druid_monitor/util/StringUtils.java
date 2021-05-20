package com.maben.druid_monitor.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

public class StringUtils {
    public static String getMsg(Exception e) {
        final StringBuilder result = new StringBuilder("操作失败!");
        if (Objects.nonNull(e)){
            result.append("，原因是：");
            final StringWriter sw = new StringWriter();
            try(PrintWriter pw = new PrintWriter(sw)){
                e.printStackTrace(pw);
                result.append(sw.toString());
            }
        }
        return result.toString();
    }
}
