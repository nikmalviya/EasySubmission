package com.project.service;

import com.project.entity.Student;
import com.project.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudentList() {
        return this.studentRepository.findAll();
    }

    public Student getStudent(int id) {
        return this.studentRepository.findById(id).orElse(null);
    }

    public void saveStudent(Student student) {
        this.studentRepository.save(student);
    }

    public void deleteStudent(Student student) {
        this.studentRepository.delete(student);
    }

    public void deleteStudentById(int id) {
        this.studentRepository.deleteById(id);
    }

    public Student getStudent(String username){
        return studentRepository.getStudentByUser_Username(username);
    }
}
