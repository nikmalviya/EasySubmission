package com.project.service;

import com.project.entity.*;
import com.project.repository.SubmissionRepository;
import com.project.student.form.SubmissionForm;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {
    private SubmissionRepository submissionRepository;
    private final StorageService storageService;

    public SubmissionService(SubmissionRepository submissionRepository, StorageService storageService) {
        this.submissionRepository = submissionRepository;
        this.storageService = storageService;
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

    public SubmissionStatus getStatus(Assignment assignment, Student student) {
        Optional<Submission> submission = submissionRepository.findByAssignmentAndStudent(assignment, student);
        return submission.isPresent() ? submission.get().getStatus() : SubmissionStatus.NOT_SUBMITTED;
    }

    public int getMarks(Assignment assignment, Student student) {
        return submissionRepository.findByAssignmentAndStudent(assignment, student).get().getMarks().intValue();
    }

    public String getFileName(Assignment assignment, Student student) {
        return submissionRepository
                .findByAssignmentAndStudent(assignment, student)
                .get()
                .getFileName();

    }

    public String getFilePath(Assignment assignment, Student student) {
        return submissionRepository
                .findByAssignmentAndStudent(assignment, student)
                .get()
                .getFilePath();

    }

    public void submitAssignment(SubmissionForm form, Subject subject, Assignment assignment, Student student) throws IOException {
        SubmissionStatus status = new Date().before(assignment.getDeadlineDate()) ? SubmissionStatus.SUBMITTED : SubmissionStatus.LATE_SUBMITTED;
        String filePath = this.storageService.uploadSubmission(form.getFile());
        Submission submission = new Submission(assignment, subject, student, status, 0.0f, filePath);
        saveSubmission(submission);

    }

    public void updateAssignment(SubmissionForm form, Subject subject, Assignment assignment, Student student) throws IOException {
        SubmissionStatus status = new Date().before(assignment.getDeadlineDate()) ? SubmissionStatus.SUBMITTED : SubmissionStatus.LATE_SUBMITTED;
        Submission submission = submissionRepository.findByAssignmentAndStudent(assignment, student).get();
//        try {
//            Files.delete(Paths.get(submission.getFilePath()));
//        } catch (NoSuchFileException ignored) {
//        }
        String filePath = this.storageService.uploadSubmission(form.getFile());
        submission.setFilePath(filePath);
//        File file = new File(filePath);
//        FileOutputStream fos = new FileOutputStream(file);
//        byte[] bytes = form.getFile().getBytes();
//        fos.write(bytes);
//        fos.close();
        saveSubmission(submission);

    }
}

