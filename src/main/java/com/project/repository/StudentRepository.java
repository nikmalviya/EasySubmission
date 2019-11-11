package com.project.repository;

import com.project.entity.Professor;
import com.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    public Student getStudentByUser_Username(String username);
}
