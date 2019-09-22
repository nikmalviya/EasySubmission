package com.project.service;

import com.project.entity.Subject;
import com.project.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }


    public List<Subject> getSubjectList() {
        return this.subjectRepository.findAll();
    }

    public Subject getSubject(int id) {
        return this.subjectRepository.findById(id).orElse(null);
    }

    public void saveSubject(Subject subject) {
        this.subjectRepository.save(subject);
    }

    public void deleteSubject(Subject subject) {
        this.subjectRepository.delete(subject);
    }
}
