package com.project.service;

import com.project.entity.Submission;
import com.project.repository.SubmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionService {
    private SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }


    public List<Submission> getSubmissionList() {
        return this.submissionRepository.findAll();
    }

    public Submission getSubmission(int id) {
        return this.submissionRepository.findById(id).orElse(null);
    }

    public void saveSubmission(Submission submission) {
        this.submissionRepository.save(submission);
    }

    public void deleteSubmission(Submission submission) {
        this.submissionRepository.delete(submission);
    }
}

