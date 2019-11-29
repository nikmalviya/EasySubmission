package com.project.repository;

import com.project.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Integer> {
    public Professor getProfessorByUser_Username(String username);
}
