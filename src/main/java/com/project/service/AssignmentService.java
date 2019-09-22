package com.project.service;

import com.project.entity.Assignment;
import com.project.repository.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {
    private AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository){
        this.assignmentRepository = assignmentRepository;
    }

    public List<Assignment> getAssignmentList(){
        return this.assignmentRepository.findAll();
    }

    public Assignment getAssignment(int id){
        return this.assignmentRepository.findById(id).orElse(null);
    }

    public void saveAssignment(Assignment assignment){
        this.assignmentRepository.save(assignment);
    }

    public void deleteAssignment(Assignment assignment){
        this.assignmentRepository.delete(assignment);
    }
}
