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

    private PasswordValidator.PasswordValidationDataWrapper dataWrapper;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isBlank()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("The password should be present and not blank")
                    .addConstraintViolation();
            return false;
        }
        return PasswordValidator.isPasswordValid(password, context, dataWrapper);
    }

    @Override
    public void initialize(RequiredPassword constraintAnnotation) {
        int minLength = constraintAnnotation.minLength();
        int maxLength = constraintAnnotation.maxLength();
        PasswordConstraint passwordConstraint = constraintAnnotation.passwordConstraint();
        dataWrapper = new PasswordValidator.PasswordValidationDataWrapper(minLength, maxLength, passwordConstraint);
    }
}
