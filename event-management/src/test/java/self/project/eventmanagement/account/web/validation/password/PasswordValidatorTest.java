package self.project.eventmanagement.account.web.validation.password;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PasswordValidatorTest {

    private static final int DEFAULT_MIN_LENGTH = 7;

    private static final int DEFAULT_MAX_LENGTH = 14;

    @Test
    void test_should_return_true_given_lenient_constraint_and_valid_password() {
        // Given
        String validPassword = "ABcde123";
        PasswordValidator.PasswordValidationDataWrapper passwordValidationDataWrapper = new PasswordValidator.PasswordValidationDataWrapper(DEFAULT_MIN_LENGTH, DEFAULT_MAX_LENGTH, PasswordConstraint.LENIENT);
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        // When
        boolean isValid = PasswordValidator.isPasswordValid(validPassword, context, passwordValidationDataWrapper);

        // Then
        assertThat(isValid).isTrue();
        verify(context, never()).disableDefaultConstraintViolation();
    }

    /**
     * This method tests a password against the following conditions:
     * <ul>
     *   <li>It must contain at least one lowercase letter.</li>
     *   <li>It must contain at least one uppercase letter.</li>
     *   <li>It must contain at least one digit.</li>
     * </ul>
     *
     * @param invalidPassword The password that is being tested.
     *
     *                        <p>Example passwords and their corresponding failed conditions:</p>
     *
     *                        <ul>
     *                          <li><code>ABC123</code>:
     *                              <ul>
     *                                <li>Fails the lowercase condition: No lowercase letter is present.</li>
     *                              </ul>
     *                          </li>
     *                          <li><code>abc123</code>:
     *                              <ul>
     *                                <li>Fails the uppercase condition: No uppercase letter is present.</li>
     *                              </ul>
     *                          </li>
     *                          <li><code>Abcdefgh</code>:
     *                              <ul>
     *                                <li>Fails the digit condition: No digit is present.</li>
     *                              </ul>
     *                          </li>
     *                        </ul>
     */
    @ParameterizedTest
    @ValueSource(strings = {"ABCDE123", "abcde123", "Abcdefgh"})
    void test_should_return_false_given_lenient_constraint_and_invalid_password(String invalidPassword) {
        // Given
        PasswordValidator.PasswordValidationDataWrapper passwordValidationDataWrapper = new PasswordValidator.PasswordValidationDataWrapper(DEFAULT_MIN_LENGTH, DEFAULT_MAX_LENGTH, PasswordConstraint.LENIENT);
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);

        when(context.buildConstraintViolationWithTemplate(PasswordConstraint.LENIENT.getConstraintViolationMessage()))
                .thenReturn(builder);

        // When
        boolean isValid = PasswordValidator.isPasswordValid(invalidPassword, context, passwordValidationDataWrapper);

        // Then
        assertThat(isValid).isFalse();
        verify(context).disableDefaultConstraintViolation();
        verify(builder).addConstraintViolation();
    }

    @Test
    void test_should_return_true_given_strong_constraint_and_valid_password() {
        // Given
        String validPassword = "Abc123!@#";
        PasswordValidator.PasswordValidationDataWrapper passwordValidationDataWrapper = new PasswordValidator.PasswordValidationDataWrapper(DEFAULT_MIN_LENGTH, DEFAULT_MAX_LENGTH, PasswordConstraint.LENIENT);
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        // When
        boolean isValid = PasswordValidator.isPasswordValid(validPassword, context, passwordValidationDataWrapper);

        // Then
        assertThat(isValid).isTrue();
        verify(context, never()).disableDefaultConstraintViolation();
    }

    /**
     * This method tests a password against the following conditions:
     * <ul>
     *   <li>It must contain at least one lowercase letter.</li>
     *   <li>It must contain at least one uppercase letter.</li>
     *   <li>It must contain at least one digit.</li>
     *   <li>It must contain at least one special character from the set [!@#$%^&*(),.?":{}|<>].</li>
     * </ul>
     *
     * @param invalidPassword The password that is being tested.
     *
     *                        <p>Example passwords and their corresponding failed conditions:</p>
     *
     *                        <ul>
     *                          <li><code>ABC123!@#</code>:
     *                              <ul>
     *                                <li>Fails the lowercase condition: No lowercase letter is present.</li>
     *                              </ul>
     *                          </li>
     *                          <li><code>abc123!@#</code>:
     *                              <ul>
     *                                <li>Fails the uppercase condition: No uppercase letter is present.</li>
     *                              </ul>
     *                          </li>
     *                          <li><code>Abcdefgh!@#</code>:
     *                              <ul>
     *                                <li>Fails the digit condition: No digit is present.</li>
     *                              </ul>
     *                          </li>
     *                          <li><code>Abc123defg</code>:
     *                              <ul>
     *                                <li>Fails the special character condition: No special character is present.</li>
     *                              </ul>
     *                          </li>
     *                        </ul>
     */

    @ParameterizedTest
    @ValueSource(strings = {"ABC123!@#", "abc123!@#", "Abcdefgh!@#", "Abc123defg"})
    void test_should_return_false_given_strong_constraint_and_invalid_password(String invalidPassword) {
        // Given
        PasswordValidator.PasswordValidationDataWrapper passwordValidationDataWrapper = new PasswordValidator.PasswordValidationDataWrapper(DEFAULT_MIN_LENGTH, DEFAULT_MAX_LENGTH, PasswordConstraint.STRONG);
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);

        when(context.buildConstraintViolationWithTemplate(PasswordConstraint.STRONG.getConstraintViolationMessage()))
                .thenReturn(builder);

        // When
        boolean isValid = PasswordValidator.isPasswordValid(invalidPassword, context, passwordValidationDataWrapper);

        // Then
        assertThat(isValid).isFalse();
        verify(context).disableDefaultConstraintViolation();
        verify(builder).addConstraintViolation();
    }

    @Test
    void test_should_return_false_given_password_smaller_than_minimum_length() {
        // Given
        String smallerPassword = "1234";
        PasswordValidator.PasswordValidationDataWrapper passwordValidationDataWrapper = new PasswordValidator.PasswordValidationDataWrapper(DEFAULT_MIN_LENGTH, DEFAULT_MAX_LENGTH, PasswordConstraint.STRONG);
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);

        when(context.buildConstraintViolationWithTemplate("The password must be at least 7 characters long"))
                .thenReturn(builder);

        // When
        boolean isValid = PasswordValidator.isPasswordValid(smallerPassword, context, passwordValidationDataWrapper);

        // Then
        assertThat(isValid).isFalse();
        verify(context).disableDefaultConstraintViolation();
        verify(builder).addConstraintViolation();
    }

    @Test
    void test_should_return_false_given_password_bigger_than_maximum_length() {
        // Given
        String biggerPassword = "123456891011121314";
        PasswordValidator.PasswordValidationDataWrapper passwordValidationDataWrapper = new PasswordValidator.PasswordValidationDataWrapper(DEFAULT_MIN_LENGTH, DEFAULT_MAX_LENGTH, PasswordConstraint.STRONG);
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);

        when(context.buildConstraintViolationWithTemplate("The password must be at most 14 characters long"))
                .thenReturn(builder);

        // When
        boolean isValid = PasswordValidator.isPasswordValid(biggerPassword, context, passwordValidationDataWrapper);

        // Then
        assertThat(isValid).isFalse();
        verify(context).disableDefaultConstraintViolation();
        verify(builder).addConstraintViolation();
    }
}