package com.elasticSearch.thirty;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elasticSearch.thirty.entity.Course;
import com.elasticSearch.thirty.mapper.CourseMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemoApplicationTests {

	@Autowired
	private CourseMapper courseMapper;

	//添加课程
	@Test
	public void addCourse(){
		Course course = new Course();
		//cid由我们设置的策略，雪花算法进行生成（至少70年内生成的id不会重复）
		course.setCname("java");
		course.setUserId(100L);
		course.setCstatus("Normal");

		courseMapper.insert(course);
	}

	//查询课程
	@Test
	public void findCourse(){
		QueryWrapper<Course> wrapper = new QueryWrapper<>();
		wrapper.eq("cid", 509755853058867201L);
		courseMapper.selectOne(wrapper);
	}
}