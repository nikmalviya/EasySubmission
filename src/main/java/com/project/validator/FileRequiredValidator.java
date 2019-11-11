package com.project.validator;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileRequiredValidator implements ConstraintValidator<FileRequired, MultipartFile> {
   public void initialize(FileRequired constraint) {
   }

   public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
      return file.getSize() != 0;
   }
}
