package com.project.repository;

import com.project.entity.Assignment;
import com.project.entity.Student;
import com.project.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission,Integer> {
    public Optional<Submission> findByAssignmentAndStudent(Assignment assignment, Student student);
}
