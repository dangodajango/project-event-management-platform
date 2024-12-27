package self.project.eventmanagement.account.web.validation.password;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class OptionalPasswordValidatorTest {

    private final ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

    private final OptionalPasswordValidator optionalPasswordValidator = new OptionalPasswordValidator(7, 14, PasswordConstraint.LENIENT);

    @Test
    void test_should_return_true_given_null_password() {
        // Given
        String nullPassword = null;

        // When
        boolean isValid = optionalPasswordValidator.isValid(nullPassword, context);

        // Then
        assertThat(isValid).isTrue();
    }

    @Test
    void test_should_return_true_given_valid_password() {
        // Given
        String validPassword = "Abc12345";

        // When
        boolean isValid = optionalPasswordValidator.isValid(validPassword, context);

        // Then
        assertThat(isValid).isTrue();
    }

    @Test
    void test_should_return_false_given_invalid_password() {
        // Given
        String invalidPassword = "short";
        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);

        when(context.buildConstraintViolationWithTemplate("The password must be at least 7 characters long"))
                .thenReturn(builder);

        // When
        boolean isValid = optionalPasswordValidator.isValid(invalidPassword, context);

        // Then
        assertThat(isValid).isFalse();
        verify(context).disableDefaultConstraintViolation();
        verify(builder).addConstraintViolation();
    }
}