package com.maben.druid_monitor.mapper;

import com.maben.druid_monitor.mapper.provider.StudentMapperProvider;
import com.maben.druid_monitor.pojo.Student;
import com.maben.druid_monitor.pojo.query.StudentQuery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface StudentMapper {
    @Insert(value = "insert into t_student (name) values (#{name})")
    void insert(Student student);

    @SelectProvider(method = "select",type = StudentMapperProvider.class)
    List<Student> select(StudentQuery studentQuery);
}
