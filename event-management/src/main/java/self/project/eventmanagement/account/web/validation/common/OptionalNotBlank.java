package self.project.eventmanagement.account.web.validation.common;

import jakarta.validation.Constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = OptionalNotBlankValidator.class)
public @interface OptionalNotBlank {

    String message() default "The field should not be blank";
}
