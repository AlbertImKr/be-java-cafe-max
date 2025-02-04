package kr.codesquad.cafe.post.annotation;


import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotBlank
@Size(max = 1000, min = 3, message = "{error.textContent.size}")
@Target({FIELD})
@ReportAsSingleViolation
@Retention(RUNTIME)
@Constraint(validatedBy = {})
public @interface ValidPostTextContent {

    String message() default "{error.textContent.size}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
