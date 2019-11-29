package com.project.service;

import com.project.entity.Assignment;
import com.project.entity.Subject;
import com.project.professor.form.AssignmentForm;
import com.project.repository.AssignmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AssignmentService {
    private AssignmentRepository assignmentRepository;
    final private StorageService storageService;

    public AssignmentService(AssignmentRepository assignmentRepository, StorageService storageService) {
        this.assignmentRepository = assignmentRepository;
        this.storageService = storageService;
    }

    public List<Assignment> getAssignmentList() {
        return this.assignmentRepository.findAll();
    }

    public Assignment getAssignment(int id) {
        return this.assignmentRepository.findById(id).orElse(null);
    }

    public void saveAssignment(Assignment assignment) {
        this.assignmentRepository.save(assignment);
    }

    public void deleteAssignment(Assignment assignment) {
        this.assignmentRepository.delete(assignment);
    }

    public void saveAssignment(AssignmentForm assignmentForm, Subject subject) throws IOException {
        MultipartFile multiPartFile = assignmentForm.getFile();
        String fileUrl = this.storageService.uploadAssignment(multiPartFile);
        System.out.println(fileUrl);
        assignmentForm.setFilePath(fileUrl);
        assignmentForm.setSubject(subject);
        Assignment assignment = new Assignment(assignmentForm);
        this.saveAssignment(assignment);
//        String url = "src/main/resources/uploads/assignments/file$$" + multiPartFile.getOriginalFilename() + "$$-" + subject.getSubjectID() + "-" + new Date().toLocaleString();
//        File file = new File(url);
//        FileOutputStream fos = new FileOutputStream(file);
//        byte[] bytes = multiPartFile.getBytes();
//        fos.write(bytes);
//        fos.close();
    }

    public void updateAssignment(Assignment assignment, AssignmentForm assignmentForm, Subject subject) throws IOException {
        MultipartFile multiPartFile = assignmentForm.getFile();
        String fileUrl = this.storageService.uploadAssignment(multiPartFile);
        assignmentForm.setFilePath(fileUrl);
        assignmentForm.setSubject(subject);
        assignment.setAssignmentTitle(assignmentForm.getAssignmentTitle());
        assignment.setDeadlineDate(assignmentForm.getDeadlinedate());
        assignment.setFilePath(assignmentForm.getFilePath());
        assignment.setNotes(assignmentForm.getNotes());
        assignment.setStatus(assignmentForm.getAssignmentStatus());
        this.saveAssignment(assignment);

    }

    public void deleteAssignmentById(int id) {
        this.assignmentRepository.deleteById(id);
    }
    //testing
}
