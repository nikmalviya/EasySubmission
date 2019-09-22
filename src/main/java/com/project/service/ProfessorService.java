package com.project.service;

import com.project.entity.Professor;
import com.project.entity.Student;
import com.project.repository.ProfessorRepository;
import com.project.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    private ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }


    public List<Professor> getProfessorList() {
        return this.professorRepository.findAll();
    }

    public Professor getProfessor(int id) {
        return this.professorRepository.findById(id).orElse(null);
    }

    public void saveProfessor(Professor professor) {
        this.professorRepository.save(professor);
    }

    public void deleteProfessor(Professor professor) {
        this.professorRepository.delete(professor);
    }
}
