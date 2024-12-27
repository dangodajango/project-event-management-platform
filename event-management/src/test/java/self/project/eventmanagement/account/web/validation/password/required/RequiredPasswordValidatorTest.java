package self.project.eventmanagement.account.web.validation.password.required;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import self.project.eventmanagement.account.web.validation.password.PasswordConstraint;
import self.project.eventmanagement.account.web.validation.password.PasswordValidator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class RequiredPasswordValidatorTest {

    private final ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

    private final RequiredPasswordValidator requiredPasswordValidator = new RequiredPasswordValidator(new PasswordValidator.PasswordValidationDataWrapper(7, 14, PasswordConstraint.LENIENT));

    @Test
    void should_return_false_given_null_password() {
        // Given
        String nullPassword = null;
        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);

        when(context.buildConstraintViolationWithTemplate("The password should be present and not blank"))
                .thenReturn(builder);

        // When
        boolean isValid = requiredPasswordValidator.isValid(nullPassword, context);

        // Then
        assertThat(isValid).isFalse();
        verify(context).disableDefaultConstraintViolation();
        verify(builder).addConstraintViolation();
    }

    @Test
    void should_return_false_given_blank_password() {
        // Given
        String blankPassword = " ";
        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);

        when(context.buildConstraintViolationWithTemplate("The password should be present and not blank"))
                .thenReturn(builder);

        // When
        boolean isValid = requiredPasswordValidator.isValid(blankPassword, context);

        // Then
        assertThat(isValid).isFalse();
        verify(context).disableDefaultConstraintViolation();
        verify(builder).addConstraintViolation();
    }

    @Test
    void should_return_false_given_invalid_password() {
        // Given
        String invalidPassword = "1234";
        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);

        when(context.buildConstraintViolationWithTemplate("The password must be at least 7 characters long"))
                .thenReturn(builder);

        // When
        boolean isValid = requiredPasswordValidator.isValid(invalidPassword, context);

        // Then
        assertThat(isValid).isFalse();
        verify(context).disableDefaultConstraintViolation();
        verify(builder).addConstraintViolation();
    }

    @Test
    void should_return_true_given_valid_password() {
        // Given
        String validPassword = "Abc12345";

        // When
        boolean isValid = requiredPasswordValidator.isValid(validPassword, context);

        // Then
        assertThat(isValid).isTrue();
    }
}