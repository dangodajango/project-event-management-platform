package self.project.eventmanagement.account.web.validation.email.required;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;

public class RequiredEmailValidator implements ConstraintValidator<RequiredEmail, String> {

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isBlank()) {
            return false;
        }
        return new EmailValidator().isValid(email, context);
    }
}
