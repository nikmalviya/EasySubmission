package com.project.student.form;

import com.project.validator.FileRequired;
import org.springframework.web.multipart.MultipartFile;

public class SubmissionForm {
    @FileRequired(message = "Submission File Required")
    private MultipartFile file;
    private String filePath;

    public SubmissionForm(MultipartFile file, String filePath) {
        this.file = file;
        this.filePath = filePath;
    }

    public SubmissionForm() {

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
