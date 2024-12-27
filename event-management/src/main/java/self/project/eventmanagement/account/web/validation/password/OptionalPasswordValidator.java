package self.project.eventmanagement.account.web.validation.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class OptionalPasswordValidator implements ConstraintValidator<OptionalPassword, String> {

    private int minLength;

    private int maxLength;

    private PasswordConstraint passwordConstraint;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return true;
        }
        return new PasswordValidator(minLength, maxLength, passwordConstraint).isValid(password, context);
    }

    @Override
    public void initialize(OptionalPassword constraintAnnotation) {
        minLength = constraintAnnotation.minLength();
        maxLength = constraintAnnotation.maxLength();
        passwordConstraint = constraintAnnotation.passwordConstraint();
    }
}
