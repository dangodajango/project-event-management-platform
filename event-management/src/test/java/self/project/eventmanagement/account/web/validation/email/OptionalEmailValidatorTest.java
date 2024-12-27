package self.project.eventmanagement.account.web.validation.email;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class OptionalEmailValidatorTest {

    private final ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

    private final OptionalEmailValidator optionalEmailValidator = new OptionalEmailValidator();

    @Test
    void test_should_return_true_if_email_is_null() {
        // Given
        String nullEmail = null;

        // When
        boolean isValid = optionalEmailValidator.isValid(nullEmail, context);

        // Then
        assertThat(isValid).isTrue();
    }

    @Test
    void test_should_return_true_given_valid_email() {
        // Given
        String validEmail = "john.doe@gmail.com";

        // When
        boolean isValid = optionalEmailValidator.isValid(validEmail, context);

        // Then
        assertThat(isValid).isTrue();
    }

    @Test
    void test_should_return_false_given_invalid_email() {
        // Given
        String invalidEmail = "john.doe";

        // When
        boolean isValid = optionalEmailValidator.isValid(invalidEmail, context);

        // Then
        assertThat(isValid).isFalse();
    }
}