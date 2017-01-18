package com.milo.springMvcTest.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.milo.springMvcTest.model.Course;
import com.milo.springMvcTest.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {
	private static Logger log = LoggerFactory.getLogger(CourseController.class);
	private CourseService courseService;
	
	@Autowired
	public void setCourseService(CourseService courseService){
		this.courseService = courseService;
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String viewCourse(@RequestParam("courseId") Integer courseId, Model model){
		log.debug("In viewCourse, courseId = {}", courseId);
		Course course = courseService.getCourseById(courseId);
		model.addAttribute(course);
		return "courseView";
	}
	
	@RequestMapping(value="/view2/{courseId}", method=RequestMethod.GET)
	public String viewCourseByRestful(@PathVariable("courseId") Integer courseId, Map<String, Object> model){
		log.debug("In viewCourse, courseId = {}", courseId);
		Course course = courseService.getCourseById(courseId);
		model.put("course", course);
		return "courseView";
	}
	
	@RequestMapping(value="/view3", method=RequestMethod.GET)
	public String viewCourseByServlet(HttpServletRequest request){
		Integer courseId = Integer.valueOf(request.getParameter("courseId"));
		log.debug("In viewCourse, courseId = {}", courseId);
		Course course = courseService.getCourseById(courseId);
		request.setAttribute("course", course);
		return "courseView";
	}
	
	@RequestMapping(value="admin", method=RequestMethod.GET, params="add")
	public String editCourse(){
		return "admin/edit";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveCourse(@ModelAttribute Course course){
		log.debug("Info of Course:");
		log.debug(ReflectionToStringBuilder.toString(course));
		
		Integer courseId = 123;
		course.setCourseId(courseId);
		return "redirect:view2/" + course.getCourseId();
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public String uploadFilePage(){
		return "admin/file";
	}
	
	@RequestMapping(value="doUpload", method=RequestMethod.POST)
	public String uploadFile(MultipartFile file) throws IOException{
		log.debug("Process file:{}", file.getOriginalFilename());
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File("D:\\upload\\", System.currentTimeMillis() + file.getOriginalFilename()));
		return "success";
	}
	
	@RequestMapping(value="/{courseId}", method=RequestMethod.GET)
	public @ResponseBody Course getCourseInJson(@PathVariable Integer courseId){
		return courseService.getCourseById(courseId);
	}
	
	@RequestMapping(value="/jsontype/{courseId}")
	public ResponseEntity<Course> getCourseInJson2(@PathVariable Integer courseId){
		Course course = courseService.getCourseById(courseId);
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}
}
