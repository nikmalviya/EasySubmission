package com.project.validator;

import com.project.professor.form.AssignmentForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AssignmentValidator implements Validator {
    // just for checking the class passed is of AssignmentForm or not
    @Override
    public boolean supports(Class<?> aClass) {
        return AssignmentForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AssignmentForm assignmentForm=(AssignmentForm) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"assignmentTitle","assignmentTitle.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"assignmentStatus","assignmentStatus.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"notes","notes.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"postdate","postdate.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"deadlinedate","deadlinedate.required");


        if(assignmentForm.getFile().getSize()==0)
        {
            errors.rejectValue("file","file.empty","File can not be empty");
        }
    }
}
