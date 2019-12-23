package com.project.admin.controller;

import com.project.admin.form.StudentExcelForm;
import com.project.admin.form.StudentForm;
import com.project.admin.form.UpdateStudentForm;
import com.project.entity.*;
import com.project.service.CourseService;
import com.project.service.StudentService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("admin/users/students")
public class StudentController {
    final private CourseService courseService;
    final private StudentService studentService;


    public StudentController(
            CourseService courseService,
            StudentService studentService
    ) {
        this.courseService = courseService;
        this.studentService = studentService;
    }
//
//    @InitBinder
//    public void initBinder(WebDataBinder dataBinder) {
//        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
//        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
//    }

    @GetMapping
    public String admins(Model model) {
        model.addAttribute("students", studentService.getStudentList());
        return "admin/students/list-students";
    }

    @PostMapping(path = "/add")
    public String saveStudent(@Valid @ModelAttribute("studentForm") StudentForm form, BindingResult result, Model model, RedirectAttributes attrs) {
        form.setCourseOptions(courseService.getCourseOptions());
        if (result.hasErrors()) {
            return "admin/students/student-form";
        }
        if(!form.getPassword().equals(form.getConfirmPassword()))
        {
            ObjectError error = new ObjectError("password_mismatch", "Password Mismatch..");
            result.addError(error);
            return "admin/students/student-form";
        }
        try {
            User user = new User(
                    form.getUsername(),
                    form.getPassword(),
                    UserType.ROLE_STUDENT,
                    form.getStatus()
            );
            Student student = new Student(
                    form.getStudentFullName(),
                    form.getDateOfBirth(),
                    form.getContactNumber(),
                    form.getAddress()
            );
            student.setUser(user);
            student.getCourses().addAll(
                    courseService.getAllCourses(form.getCourses())
            );
            studentService.saveStudent(student);
        } catch (DataIntegrityViolationException e) {
            ObjectError error = new ObjectError("already_exist_error", "Username Already Exists..");
            result.addError(error);
            return "admin/students/student-form";
        }
        attrs.addFlashAttribute("success_message", "Student Added Successfully..");
        return "redirect:/admin/users/students";
    }

    @PostMapping(path = "/add/excel")
    public String saveStudents(@Valid @ModelAttribute("studentExcelForm") StudentExcelForm form, BindingResult result, Model model, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            return "admin/students/student-excel-form";
        }
        try {
            File file= File.createTempFile("Students",".xlsx");
            FileOutputStream fos=new FileOutputStream(file);
            fos.write(form.getExcelFile().getBytes());
            fos.close();

            //FileInputStream fileInputStream=new FileInputStream(file);
            XSSFWorkbook workbook=new XSSFWorkbook(file);
            XSSFSheet sheet=workbook.getSheetAt(0);
            Iterator<Row> itr=sheet.iterator();
            boolean flag=true;
            while (itr.hasNext()) {
                if(flag){
                    Row row = itr.next();
                    flag=false;
                }
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                User user = null;
                Cell cell = cellIterator.next();
                //User object
                user = new User(cell,cellIterator);
//                user.setUsername(cell.getStringCellValue());
//                cell = cellIterator.next();
//                user.setPassword(cell.getStringCellValue());
//                user.setUserType(UserType.ROLE_STUDENT);
//                cell = cellIterator.next();
//                user.setUserStatus(cell.getStringCellValue().equals("ACTIVE") ? UserStatus.ACTIVE : UserStatus.INACTIVE);//ACTIVE,INACTIVE
//                //student object
                Student student = new Student(cell,cellIterator);
                student.setUser(user);
                cell = cellIterator.next();
                List<String> courses = Arrays.asList(cell.getStringCellValue().split(","));
                List<Course> courses1 = new ArrayList<>();
                for (String course : courses) {
                    courses1.add(courseService.getCourse(course));
                }
                student.setCourses(courses1);
                this.studentService.saveStudent(student);
            }

        } catch (DataIntegrityViolationException e) {
            ObjectError error = new ObjectError("already_exist_error", "Username Already Exists..");
            result.addError(error);
            return "admin/students/student-excel-form";
        } catch (IOException e) {
            ObjectError error = new ObjectError("Wrong_File_Format", "Error While Accessing the file....");
            result.addError(error);
            return "admin/students/student-excel-form";
        }  catch (ParseException e) {
            ObjectError error = new ObjectError("Wrong_date_format", "Check the syntax of DOB..");
            result.addError(error);
            return "admin/students/student-excel-form";
        } catch (InvalidFormatException e) {
            ObjectError error = new ObjectError("Wrong_File_Format", "File should be in Excel Format");
            result.addError(error);
            return "admin/students/student-excel-form";
        }
        catch (Exception e)
        {
            ObjectError error = new ObjectError("Wrong_File_Format", "Please check the Format of File.");
            result.addError(error);
            return "admin/students/student-excel-form";
        }
        attrs.addFlashAttribute("success_message", "Student Added Successfully..");
        return "redirect:/admin/users/students";
    }


        @GetMapping("/add/excel")
    public String studentsForm(Model model) {
        StudentExcelForm studentExcelForm=new StudentExcelForm();
        model.addAttribute("studentExcelForm",studentExcelForm);
        return "admin/students/student-excel-form";
    }


    @GetMapping(path = "/add")
    public String studentForm(Model model) {
        StudentForm studentForm = new StudentForm(courseService.getCourseOptions());
        model.addAttribute("studentForm", studentForm);
        return "admin/students/student-form";
    }

    @GetMapping(path = "/update/{id}/")
    public String updateStudentForm(@PathVariable("id") int id, Model model) {
        Student student = studentService.getStudent(id);
        UpdateStudentForm studentForm = new UpdateStudentForm(courseService.getCourseOptions());
        studentForm.setStudentData(student);
        model.addAttribute("studentForm", studentForm);
        model.addAttribute("updatemode", true);
        model.addAttribute("id", String.valueOf(id));
        return "admin/students/student-form";
    }

    @PostMapping("/update/{id}/")
    public String updateStudent(@Valid @ModelAttribute("studentForm") UpdateStudentForm form, BindingResult result, @PathVariable("id") int id, Model model, RedirectAttributes attrs) {
        form.setCourseOptions(courseService.getCourseOptions());
        if (result.hasErrors()) {
            model.addAttribute("updatemode", true);
            return "admin/students/student-form";
        }
        try {
            Student student = studentService.getStudent(id);
            User user = student.getUser();
            user.setUsername(form.getUsername());
            user.setUserStatus(form.getStatus());
            student.setStudentFullName(form.getStudentFullName());
            student.setDateOfBirth(form.getDateOfBirth());
            student.setContactNumber(form.getContactNumber());
            student.setAddress(form.getAddress());
            student.setCourses(courseService.getAllCourses(form.getCourses()));
            studentService.saveStudent(student);
        } catch (DataIntegrityViolationException e) {
            ObjectError error = new ObjectError("already_exist_error", "Admin User Already Exists..");
            result.addError(error);
            model.addAttribute("updatemode", true);
            return "admin/students/students-form";
        }
        attrs.addFlashAttribute("success_message", "Student Updated Successfully..");
        return "redirect:/admin/users/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id, RedirectAttributes attrs) {
        studentService.deleteStudentById(id);
        attrs.addFlashAttribute("success_message", "Student Deleted Successfully...");
        return "redirect:/admin/users/students";
    }

}
