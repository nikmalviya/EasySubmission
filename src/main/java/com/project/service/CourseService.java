package com.project.service;

import com.project.entity.Course;
import com.project.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public CourseService() {
    }

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourseList() {
        //Sort.by("courseID")
        try {

            return this.courseRepository.findAll();
        }
        catch (Exception ex)
        {
            System.out.println("you got null");
        }
        return null;
    }

    public Course getCourse(int id) {
        return this.courseRepository.findById(id).orElse(null);
    }

    public void saveCourse(Course course) {
        this.courseRepository.save(course);
    }

    public void deleteCourse(Course course) {
        this.courseRepository.delete(course);
    }
    public void deleteCourseById(int id){
        this.courseRepository.deleteById(id);
    }

    //function to get all courses in LinkedHashMap to directly link it with form:options tag
    public LinkedHashMap<Integer,String> getCourseOptions(){
        LinkedHashMap<Integer,String> courseOptions=new LinkedHashMap<>();
        List<Course> courses=this.getCourseList();
        System.out.println("calling course Options from course service");
        System.out.println(courses);
        courses.forEach(course -> courseOptions.put(course.getCourseID(),course.getCourseTitle()));
        return courseOptions;
    }
}
