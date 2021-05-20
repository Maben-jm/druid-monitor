package com.maben.druid_monitor.pojo.query;

import com.maben.druid_monitor.pojo.Student;
import lombok.Data;

@Data
public class StudentQuery extends Student {
    private int unbounded;
}
