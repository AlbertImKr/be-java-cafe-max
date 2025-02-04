package kr.codesquad.cafe.user.annotation;


import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotBlank
@Size(max = 32, min = 8, message = "{error.password.size}")
@Pattern(regexp = "^(.*[a-z]+.*[1-9]+.*)|(.*[1-9]+.*[a-z]+.*)$", message = "{error.password.pattern}")
@Target({FIELD})
@ReportAsSingleViolation
@Retention(RUNTIME)
@Constraint(validatedBy = {})
public @interface ValidPassword {

    String message() default "{error.password.pattern}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
