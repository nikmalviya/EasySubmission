package com.project.service;

import com.project.entity.Course;
import com.project.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourseList(){
        return this.courseRepository.findAll();
    }

    public Course getCourse(int id){
        return this.courseRepository.findById(id).orElse(null);
    }

    public void saveCourse(Course course){
        this.courseRepository.save(course);
    }

    public void deleteCourse(Course course){
        this.courseRepository.delete(course);
    }
}
