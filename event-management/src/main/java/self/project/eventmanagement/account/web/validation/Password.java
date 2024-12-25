package self.project.eventmanagement.account.web.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
public @interface Password {

    int minLength() default 7;

    int maxLength() default 14;

    PasswordConstraint passwordConstraint() default PasswordConstraint.STRONG;
}
