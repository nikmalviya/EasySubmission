package com.project.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FileRequiredValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileRequired {
    String message() default "File Required";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
