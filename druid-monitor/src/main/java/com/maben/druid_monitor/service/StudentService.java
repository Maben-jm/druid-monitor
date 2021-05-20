package com.maben.druid_monitor.service;

import com.maben.druid_monitor.pojo.Student;
import com.maben.druid_monitor.pojo.query.StudentQuery;

import java.util.List;

public interface StudentService {
    public void save(Student student)throws Exception;
    public List<Student> getStudents(StudentQuery studentQuery)throws Exception;
}
