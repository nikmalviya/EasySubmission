package com.project.service;

import com.project.entity.Assignment;
import com.project.entity.Subject;
import com.project.professor.form.AssignmentForm;
import com.project.repository.AssignmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
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

    public void saveAssignment(AssignmentForm assignmentForm, Subject subject) throws IOException {
        MultipartFile multiPartFile=assignmentForm.getFile();
        String url="src/main/resources/uploads/assignments/"+multiPartFile.getOriginalFilename()+"-"+subject.getSubjectID()+"-"+new Date().toLocaleString();
        File file=new File(url);
        FileOutputStream fos = new FileOutputStream(file);
        byte[] bytes = multiPartFile.getBytes();
        fos.write(bytes);
        fos.close();
        assignmentForm.setFilePath(url);
        assignmentForm.setSubject(subject);
        Assignment assignment=new Assignment(assignmentForm);
        System.out.println("Save Assignment from service");
        this.saveAssignment(assignment);


    }

    public void updateAssignment(Assignment assignment,AssignmentForm assignmentForm, Subject subject) throws IOException {

        MultipartFile multiPartFile=assignmentForm.getFile();
        String url="src/main/resources/uploads/assignments/"+multiPartFile.getOriginalFilename()+"-"+subject.getSubjectID()+"-"+ new Date().toString();
//        String url="/home/mananmistry/Projects/SEM5/Final/OnlineSubmissionSystem/src/main/webapp/WEB-INF/uploads/"+multiPartFile.getOriginalFilename();
        File file=new File(url);
        FileOutputStream fos = new FileOutputStream(file);
        byte[] bytes = multiPartFile.getBytes();
        fos.write(bytes);
        fos.close();
        Files.delete(Paths.get(assignment.getFilePath()));
        assignmentForm.setFilePath(url);
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
