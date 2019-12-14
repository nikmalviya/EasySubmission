package com.project.service;

import com.project.entity.*;
import com.project.repository.StudentRepository;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    @Autowired
    private CourseService courseService;

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

    public Student getStudent(String username) {
        return studentRepository.getStudentByUser_Username(username);
    }
//
//    public void saveStudents(MultipartFile excelFile) throws IOException, InvalidFormatException, ParseException {
//
//  ]      }


}
