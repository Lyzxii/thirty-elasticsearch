package com.elasticSearch.thirty.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elasticSearch.thirty.entity.Course;
import com.elasticSearch.thirty.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ：yanpeidong371
 * @description：
 * @date : 2022年02月09日
 * @since: 1.0.0
 */
@RestController
public class TestController {

    @Autowired
    private CourseMapper courseMapper;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //添加课程
    @RequestMapping("/add")
    public void addCourse() throws ParseException {
        Course course = new Course();
        //cid由我们设置的策略，雪花算法进行生成（至少70年内生成的id不会重复）
        course.setCid(999L);
        course.setCname("8888红");
        course.setUserId(999L);
        course.setCstatus("Normal9");
        //course.setCreateTime(simpleDateFormat.parse("2022-01-24 12:29:22"));

        courseMapper.insert(course);

        Course course2 = new Course();
        //cid由我们设置的策略，雪花算法进行生成（至少70年内生成的id不会重复）
        course2.setCid(888L);
        course2.setCname("蔷888薇");
        course2.setUserId(888L);
        course2.setCstatus("Normal888");
        Date date = simpleDateFormat.parse("2022-03-14 12:29:22");
        course2.setCreateTime(date);


        courseMapper.insert(course2);


    }

    //查询课程
    @RequestMapping("/find")
    public void findCourse() throws ParseException {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", 3021L);
        wrapper.eq("create_time", simpleDateFormat.parse("2022-03-14 12:29:22"));
        List<Course> courses = courseMapper.selectList(wrapper);
        for (Course course : courses){
            System.out.println(course.getCname());
        }

        System.out.println("==========");

        QueryWrapper<Course> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("user_id", 3021L);
        List<Course> courses2 = courseMapper.selectList(wrapper2);
        System.out.println("size:{}"+  courses2.size());
        for (Course course : courses2){
            System.out.println(course.getCname());
        }
    }
    public static void main(String[] args){
    }
}
