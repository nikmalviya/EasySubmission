package com.project.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private int subjectID;
    @Column(name = "subject_name")
    private String subjectName;
    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToMany(mappedBy = "subject",cascade = {
            CascadeType.ALL,
    })
    private List<Assignment> assignments;
    public Subject() {
    }

    public Subject(String subjectName, Course course, Professor professor) {
        this.subjectName = subjectName;
        this.course = course;
        this.professor = professor;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectID=" + subjectID +
                ", subjectName='" + subjectName + '\'' +
                ", course=" + course +
                ", professor=" + professor +
                '}';
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
    public void addAssignment(Assignment assignment)
    {
        if(this.assignments==null){
            this.assignments=new ArrayList<>();
        }
        this.assignments.add(assignment);
    }
}
