package com.maben.druid_monitor.controller;

import com.maben.druid_monitor.pojo.Student;
import com.maben.druid_monitor.pojo.base.Code;
import com.maben.druid_monitor.pojo.base.Result;
import com.maben.druid_monitor.pojo.query.StudentQuery;
import com.maben.druid_monitor.service.StudentService;
import com.maben.druid_monitor.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
@Slf4j(topic = "m.StudentController")
@RestController
@RequestMapping("student")
public class StudentController {
    @Resource
    private StudentService studentService;

    @RequestMapping("get")
    public Result<List> get(StudentQuery studentQuery){
        final Result<List> result = new Result<>();
        try {
            final List<Student> students = studentService.getStudents(studentQuery);
            result.setT(students);
            result.setCode(Code.SUCCESS.getCode());
            result.setMsg(Code.SUCCESS.getMsg());
        } catch (Exception e) {
            result.setCode(Code.FAIL.getCode());
            result.setMsg(StringUtils.getMsg(e));
            log.error("get student error:{}",StringUtils.getMsg(e));
        }
        return result;
    }

    @RequestMapping("save/{name}")
    public Result save(@PathVariable String name) {
        final Result result = new Result();
        try {
            if (Objects.isNull(name)) {
                throw new Exception("学生姓名不能为空！！");
            }
            final Student student = new Student();
            student.setName(name);
            studentService.save(student);
            result.setCode(Code.SUCCESS.getCode());
            result.setMsg(Code.SUCCESS.getMsg());
        } catch (Exception e) {
            result.setCode(Code.FAIL.getCode());
            result.setMsg(StringUtils.getMsg(e));
            log.error("save student error:{}", e.getMessage());
        }
        return result;
    }

}
