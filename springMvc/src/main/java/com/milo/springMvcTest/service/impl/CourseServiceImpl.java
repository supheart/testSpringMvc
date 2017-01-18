package com.milo.springMvcTest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.milo.springMvcTest.model.Chapter;
import com.milo.springMvcTest.model.Course;
import com.milo.springMvcTest.service.CourseService;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

	public Course getCourseById(Integer courseId) {
		Course course = new Course();
		
		course.setCourseId(courseId);
		course.setTitle("����ǳ��Java���߳�");
		course.setImgPath("resources/imgs/course-img.jpg");
		course.setLearningNum(12345);
		course.setLevel(2);
		course.setLevelDesc("�м�");
		course.setDuration(7200l);
		course.setDescr("���߳����ճ������еĳ���֪ʶ��Ҳ������֪ʶ��bala bala...");
		
		List<Chapter> chapterList = new ArrayList<Chapter>();
		warpChapterList(course, chapterList);
		course.setChapterList(chapterList);
		
		return course;
	}
	
	private void warpChapterList(Course course,List<Chapter> chapterList) {
		Chapter chapter = new Chapter();
		chapter.setId(1);
		chapter.setCourseId(course.getCourseId());
		chapter.setOrder(1);
		chapter.setTitle("��1�� ���̱߳���֪ʶ����");
		chapter.setDescr("���½���������̱߳����صı�������");	
		chapterList.add(chapter);
		
		chapter = new Chapter();
		chapter.setId(2);
		chapter.setCourseId(course.getCourseId());
		chapter.setOrder(2);
		chapter.setTitle("��2�� Java �̳߳�����");
		chapter.setDescr("Java���Բ�����̵߳�֧�֣���δ�����������ֹͣ�̡߳����ʹ�ó��õ��̷߳�������������������̵߳Ĵ���");
		chapterList.add(chapter);
		
		chapter = new Chapter();
		chapter.setId(3);
		chapter.setCourseId(course.getCourseId());
		chapter.setOrder(3);
		chapter.setTitle("��3�� Java �̵߳���ȷֹͣ");
		chapter.setDescr("�������������ȷ��ֹͣһ���̣߳���Ҫ�߳�ͣ���ˣ������߳�ͣ�úá�");		
		chapterList.add(chapter);
		
		chapter = new Chapter();
		chapter.setId(4);
		chapter.setCourseId(course.getCourseId());
		chapter.setOrder(4);
		chapter.setTitle("��4�� �߳̽���");
		chapter.setDescr("�����������̵߳Ľ������������ĳ���Ԥ����");		
		chapterList.add(chapter);
		
		chapter = new Chapter();
		chapter.setId(5);
		chapter.setCourseId(course.getCourseId());
		chapter.setOrder(5);
		chapter.setTitle("��5�� ����չ��");
		chapter.setDescr("�򵥽��� Java ������ص��࣬�����õĶ��̱߳��ģ�͡�");		
		chapterList.add(chapter);
		
		course.setChapterList(chapterList);
	}
}
