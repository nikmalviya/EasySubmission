package com.project.service;

import com.project.entity.Course;
import com.project.repository.CourseRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class CourseService {
    final CourseRepository courseRepository;

    public CourseService(
            CourseRepository courseRepository
    ) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourseList() {
        return courseRepository.findAll(Sort.by("courseID"));
    }

    public Course getCourse(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public void deleteCourseById(int id) {
        courseRepository.deleteById(id);
    }

    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }

    public LinkedHashMap<Integer, String> getCourseOptions() {
        LinkedHashMap options = new LinkedHashMap<>();
        this.getCourseList().forEach(course -> options.put(course.getCourseID(), course.getCourseTitle()));
        return options;
    }
}
