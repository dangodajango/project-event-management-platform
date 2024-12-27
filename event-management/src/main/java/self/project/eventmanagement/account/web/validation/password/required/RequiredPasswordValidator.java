package self.project.eventmanagement.account.web.validation.password.required;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import self.project.eventmanagement.account.web.validation.password.PasswordConstraint;
import self.project.eventmanagement.account.web.validation.password.PasswordValidator;

@NoArgsConstructor
@AllArgsConstructor
public class RequiredPasswordValidator implements ConstraintValidator<RequiredPassword, String> {

    private PasswordValidator.PasswordValidationDataWrapper passwordValidationData;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isBlank()) {
            return false;
        }
        return PasswordValidator.isPasswordValid(password, context, passwordValidationData);
    }

    @Override
    public void initialize(RequiredPassword constraintAnnotation) {
        int minLength = constraintAnnotation.minLength();
        int maxLength = constraintAnnotation.maxLength();
        PasswordConstraint passwordConstraint = constraintAnnotation.passwordConstraint();
        passwordValidationData = new PasswordValidator.PasswordValidationDataWrapper(minLength, maxLength, passwordConstraint);
    }
}
