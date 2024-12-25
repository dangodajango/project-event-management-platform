package self.project.eventmanagement.account.web.validation.email;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;

public class OptionalEmailValidator implements ConstraintValidator<OptionalEmail, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || new EmailValidator().isValid(value, context);
    }
}
