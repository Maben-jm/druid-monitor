package com.maben.druid_monitor;

import com.maben.druid_monitor.pojo.Student;
import com.maben.druid_monitor.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class StudentServiceTest {

    @Resource
    private StudentService studentService;

    @Test
    public void save()throws Exception{
        final Student student = new Student();
        student.setName("test-"+ System.nanoTime());
        studentService.save(student);
    }

}
