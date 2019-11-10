package com.project.service;

import com.project.entity.Professor;
import com.project.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
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

    public void deleteProfessorById(int id) {
        this.professorRepository.deleteById(id);
    }

    public Professor getProfessor(String username){
        return professorRepository.getProfessorByUser_Username(username);
    }
    //function to get all courses in LinkedHashMap to directly link it with form:options tag
    public LinkedHashMap<Integer, String> getProfessorOptions() {
        LinkedHashMap<Integer, String> professorOptions = new LinkedHashMap<>();
        List<Professor> professors = this.getProfessorList();
        professors.forEach(professor -> professorOptions.put(professor.getProfessorID(), professor.getProfessorFullName()));
        return professorOptions;
    }


}
