package com.dong.eduservice.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dong.commonutils.R;
import com.dong.eduservice.entity.EduTeacher;
import com.dong.eduservice.entity.vo.TeacherQuery;
import com.dong.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-02-23
 */
@Api("讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    // 把service注入
    @Autowired
   private EduTeacherService teacherService;

    //1 查询讲师所有数据
    //rest风格
    @GetMapping("findAll")
    @ApiOperation(value = "讲师查询")
    public R findAllTeacher(){

        //调用service的方法实现查询所有操作
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }
    //2 (配置逻辑删除插件 config)讲师逻辑删除
    @ApiOperation(value = "逻辑删除")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id",value = "讲师ID",required = true) @PathVariable String id){

        boolean flag =  teacherService.removeById(id);
        return flag?R.ok():R.error();
    }
    //分页查询
    @ApiOperation(value = "讲师分页查询")
    @GetMapping("pageTeacher/{page}/{limit}")
    public R pageList(@ApiParam(name = "page",value = "当前页码",required = true)
                      @PathVariable Long page,
                      @ApiParam(name = "limit",value = "每页记录数",required = true)
                      @PathVariable Long limit){
         //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        //调用方法实现分页
        //调用方法时候,底层封装,把分页所有数据封装到pageTeacheer对象里面
        teacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//每页List集合数据
        return R.ok().data("total",total).data("rows",records);


    }
    //4条件查询带分页
    @GetMapping("pageTeacherCondition/{current}/{limit}")
    @ApiOperation("分页讲师列表")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit, TeacherQuery teacherQuery){

        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //wrapper 多条件组成查询
        //mybatis动态SQL
        //判断条件值是否为空,如果不为空拼接条件
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
            //构建条件
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            //构建条件
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            //构建条件  ge 大于等于
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            //构建条件 le 小于等于
            wrapper.le("gmt_create",end);
        }

        //调用方法实现条件查询分页
        teacherService.page(pageTeacher,wrapper);
        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records =  pageTeacher.getRecords();//数据list集合
        return R.ok().data("total",total).data("records",records);
    }


}

