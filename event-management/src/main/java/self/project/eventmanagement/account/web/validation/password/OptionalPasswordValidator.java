package self.project.eventmanagement.account.web.validation.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OptionalPasswordValidator implements ConstraintValidator<OptionalPassword, String> {

    private int minLength;

    private int maxLength;

    private PasswordConstraint passwordConstraint;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || new PasswordValidator(minLength, maxLength, passwordConstraint).isValid(value, context);
    }

    @Override
    public void initialize(OptionalPassword constraintAnnotation) {
        minLength = constraintAnnotation.minLength();
        maxLength = constraintAnnotation.maxLength();
        passwordConstraint = constraintAnnotation.passwordConstraint();
    }
}
