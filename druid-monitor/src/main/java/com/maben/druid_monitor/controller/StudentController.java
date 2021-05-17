package com.maben.druid_monitor.controller;

import com.maben.druid_monitor.pojo.Student;
import com.maben.druid_monitor.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j(topic = "m.StudentController")
@RestController
@RequestMapping("student")
public class StudentController {
    @Resource
    private StudentService studentService;

    @RequestMapping("save/{name}")
    public String save(@PathVariable String name) {
        if (Objects.isNull(name)) {
            return "success";
        }
        try {
            final Student student = new Student();
            student.setName(name);
            studentService.save(student);
            return "success";
        } catch (Exception e) {
            log.error("save student error:{}", e.getMessage());
            e.printStackTrace();
            return "error:" + e.getMessage();
        }
    }

}
