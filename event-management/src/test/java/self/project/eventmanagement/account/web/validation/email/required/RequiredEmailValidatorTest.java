package self.project.eventmanagement.account.web.validation.email.required;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class RequiredEmailValidatorTest {

    private final ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

    private final RequiredEmailValidator requiredEmailValidator = new RequiredEmailValidator();

    @Test
    void test_should_return_false_given_null_email() {
        // Given
        String nullEmail = null;

        // When
        boolean isValid = requiredEmailValidator.isValid(nullEmail, context);

        // Then
        assertThat(isValid).isFalse();
    }

    @Test
    void test_should_return_false_given_blank_email() {
        // Given
        String blankEmail = " ";

        // When
        boolean isValid = requiredEmailValidator.isValid(blankEmail, context);

        // Then
        assertThat(isValid).isFalse();
    }

    @Test
    void test_should_return_false_given_invalid_email() {
        // Given
        String invalidEmail = "john.doe.com";

        // When
        boolean isValid = requiredEmailValidator.isValid(invalidEmail, context);

        // Then
        assertThat(isValid).isFalse();
    }

    @Test
    void test_should_return_true_given_valid_email() {
        // Given
        String validEmail = "john.doe@gmail.com";

        // When
        boolean isValid = requiredEmailValidator.isValid(validEmail, context);

        // Then
        assertThat(isValid).isTrue();
    }
}