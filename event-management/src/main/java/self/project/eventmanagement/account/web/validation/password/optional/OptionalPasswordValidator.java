package self.project.eventmanagement.account.web.validation.password.optional;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import self.project.eventmanagement.account.web.validation.password.PasswordConstraint;
import self.project.eventmanagement.account.web.validation.password.PasswordValidator;

@AllArgsConstructor
@NoArgsConstructor
public class OptionalPasswordValidator implements ConstraintValidator<OptionalPassword, String> {

    private PasswordValidator.PasswordValidationDataWrapper passwordValidationDataWrapper;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return true;
        }
        return PasswordValidator.isPasswordValid(password, context, passwordValidationDataWrapper);
    }

    @Override
    public void initialize(OptionalPassword constraintAnnotation) {
        int minLength = constraintAnnotation.minLength();
        int maxLength = constraintAnnotation.maxLength();
        PasswordConstraint passwordConstraint = constraintAnnotation.passwordConstraint();
        passwordValidationDataWrapper = new PasswordValidator.PasswordValidationDataWrapper(minLength, maxLength, passwordConstraint);
    }
}
