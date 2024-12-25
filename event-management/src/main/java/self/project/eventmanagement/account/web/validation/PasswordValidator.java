package self.project.eventmanagement.account.web.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private int minLength;

    private int maxLength;

    private PasswordConstraint passwordConstraint;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return isPasswordSmallerThanMinRequiredPasswordSize(password, context)
               && isPasswordBiggerThanMaxAllowedPasswordSize(password, context)
               && isPasswordRespectingTheRequiredPasswordConstraint(password, context);
    }

    private boolean isPasswordSmallerThanMinRequiredPasswordSize(String password, ConstraintValidatorContext context) {
        if (password.length() > minLength) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("The password must be at least %s characters long".formatted(minLength))
                .addConstraintViolation();
        return false;
    }

    private boolean isPasswordBiggerThanMaxAllowedPasswordSize(String password, ConstraintValidatorContext context) {
        if (password.length() < maxLength) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("The password must be at most %s characters long".formatted(maxLength))
                .addConstraintViolation();
        return false;
    }

    private boolean isPasswordRespectingTheRequiredPasswordConstraint(String password, ConstraintValidatorContext context) {
        if (password.matches(passwordConstraint.getConstraintsRegex())) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(passwordConstraint.getConstraintViolationMessage())
                .addConstraintViolation();
        return false;
    }

    @Override
    public void initialize(Password constraintAnnotation) {
        minLength = constraintAnnotation.minLength();
        maxLength = constraintAnnotation.maxLength();
        passwordConstraint = constraintAnnotation.passwordConstraint();
    }
}
