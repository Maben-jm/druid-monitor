package com.maben.druid_monitor.service;

import com.maben.druid_monitor.mapper.StudentMapper;
import com.maben.druid_monitor.pojo.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Override
    public void save(Student student) throws Exception {
        if (Objects.isNull(student)){
            return;
        }
        studentMapper.insert(student);
    }
}
