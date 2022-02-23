package com.dong.eduservice.controller;


import com.dong.eduservice.entity.EduTeacher;
import com.dong.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-02-23
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    // 把service注入
    @Autowired
   private EduTeacherService teacherService;

    //1 查询讲师所有数据
    //rest风格
    @GetMapping("findAll")
    public List<EduTeacher> findAllTeacher(){

        //调用service的方法实现查询所有操作
        List<EduTeacher> list = teacherService.list(null);
        return list;
    }

}

