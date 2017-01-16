package com.milo.springMvcTest.service;

import org.springframework.stereotype.Service;

import com.milo.springMvcTest.model.Course;

@Service
public interface CourseService {
	Course getCourseById(Integer courseId);
}
