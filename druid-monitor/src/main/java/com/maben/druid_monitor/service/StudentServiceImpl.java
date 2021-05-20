package com.maben.druid_monitor.service;

import com.maben.druid_monitor.mapper.StudentMapper;
import com.maben.druid_monitor.pojo.Student;
import com.maben.druid_monitor.pojo.query.StudentQuery;
import com.maben.druid_monitor.util.ThreadUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public void save(Student student) throws Exception {
        if (Objects.isNull(student)) {
            return;
        }
        studentMapper.insert(student);
    }

    @Override
    public List<Student> getStudents(StudentQuery studentQuery) throws Exception {
        if (Objects.isNull(studentQuery)) {
            return new ArrayList<>();
        }
        final List<Student> students = studentMapper.select(studentQuery);
        final List<Future> futures = new ArrayList<>();
        students.forEach(student -> {
            final Runnable runnable = () -> student.setName(student.getName() + "--已加密");
            if (studentQuery.getUnbounded() == 1) {
                futures.add(ThreadUtil.threadPoolUnbounded.submit(runnable));
            } else {
                futures.add(ThreadUtil.threadPool.submit(runnable));
            }
        });
        futures.forEach(future -> {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return students;
    }
}
