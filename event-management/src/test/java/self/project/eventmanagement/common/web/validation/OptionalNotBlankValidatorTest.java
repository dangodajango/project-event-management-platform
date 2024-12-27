package self.project.eventmanagement.common.web.validation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class OptionalNotBlankValidatorTest {

    private final ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

    private final OptionalNotBlankValidator optionalNotBlankValidator = new OptionalNotBlankValidator();

    @Test
    void test_should_return_true_if_value_is_null() {
        // Given
        String value = null;

        // When
        boolean isValid = optionalNotBlankValidator.isValid(value, context);

        // Then
        assertThat(isValid).isTrue();
    }

    @Test
    void test_should_return_true_if_value_is_not_blank() {
        // Given
        String value = "notBlank";

        // When
        boolean isValid = optionalNotBlankValidator.isValid(value, context);

        // Then
        assertThat(isValid).isTrue();
    }

    @Test
    void test_should_return_false_if_value_is_blank() {
        // Given
        String value = " ";

        // When
        boolean isValid = optionalNotBlankValidator.isValid(value, context);

        // Then
        assertThat(isValid).isFalse();
    }
}