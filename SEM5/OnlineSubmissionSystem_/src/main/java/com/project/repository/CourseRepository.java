package com.project.repository;

import com.project.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findByCourseTitleLike(String course);
}
