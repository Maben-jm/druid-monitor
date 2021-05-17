package com.maben.druid_monitor.mapper;

import com.maben.druid_monitor.pojo.Student;
import org.apache.ibatis.annotations.Insert;

public interface StudentMapper {
    @Insert(value = "insert into t_student (name) values (#{name})")
    void insert(Student student);
}
