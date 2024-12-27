package self.project.eventmanagement.account.web.validation.password;

import jakarta.validation.ConstraintValidatorContext;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class PasswordValidator {

    public static boolean isPasswordValid(String password, ConstraintValidatorContext context, PasswordValidationDataWrapper dataWrapper) {
        return isPasswordSmallerThanMinRequiredPasswordSize(password, context, dataWrapper)
               && isPasswordBiggerThanMaxAllowedPasswordSize(password, context, dataWrapper)
               && isPasswordRespectingTheRequiredPasswordConstraint(password, context, dataWrapper);
    }

    private static boolean isPasswordSmallerThanMinRequiredPasswordSize(String password, ConstraintValidatorContext context, PasswordValidationDataWrapper dataWrapper) {
        if (password.length() >= dataWrapper.minLength()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("The password must be at least %s characters long".formatted(dataWrapper.minLength()))
                .addConstraintViolation();
        return false;
    }

    private static boolean isPasswordBiggerThanMaxAllowedPasswordSize(String password, ConstraintValidatorContext context, PasswordValidationDataWrapper dataWrapper) {
        if (password.length() <= dataWrapper.maxLength()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("The password must be at most %s characters long".formatted(dataWrapper.maxLength()))
                .addConstraintViolation();
        return false;
    }

    private static boolean isPasswordRespectingTheRequiredPasswordConstraint(String password, ConstraintValidatorContext context, PasswordValidationDataWrapper dataWrapper) {
        if (password.matches(dataWrapper.passwordConstraint().getConstraintsRegex())) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(dataWrapper.passwordConstraint().getConstraintViolationMessage())
                .addConstraintViolation();
        return false;
    }

    public record PasswordValidationDataWrapper(int minLength, int maxLength, PasswordConstraint passwordConstraint) {
    }
}
