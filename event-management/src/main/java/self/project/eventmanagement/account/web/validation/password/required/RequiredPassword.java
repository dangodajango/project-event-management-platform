package self.project.eventmanagement.account.web.validation.password.required;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import self.project.eventmanagement.account.web.validation.password.PasswordConstraint;
import self.project.eventmanagement.account.web.validation.password.PasswordValidator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This annotation should be applied to DTOs containing a password field.
 * It validates the password based on the data configured in the annotation's fields.
 */
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = RequiredPasswordValidator.class)
public @interface RequiredPassword {

    /**
     * Specifies the minimum required length for the password.
     * <p>
     * The default value is 7. This value can be customized when applying the annotation.
     */
    int minLength() default 7;

    /**
     * Specifies the maximum allowed length for the password.
     * <p>
     * The default value is 14. This value can be customized when applying the annotation.
     */
    int maxLength() default 14;

    /**
     * Defines the constraints that the password must follow, such as requirements for
     * uppercase and lowercase letters, digits, or special characters.
     * <p>
     * The default value is {@link PasswordConstraint#STRONG}, which enforces stricter password rules.
     * This can be customized to apply different levels of password strength requirements.
     */
    PasswordConstraint passwordConstraint() default PasswordConstraint.STRONG;

    /**
     * Defines the default error message to be used when the validation fails.
     * <p>
     * This method is required by the Bean Validation API. When a field annotated with this constraint
     * fails validation, the value defined in {@code message()} is used to construct the error message.
     * This message is then embedded in the validation exception thrown by the validation framework,
     * which can be presented to the user or logged for debugging purposes.
     * </p>
     * <p>
     * The default message can be overridden when applying the annotation to a field, allowing for more
     * specific and context-dependent error messages.
     * </p>
     *
     * @return the default error message
     */
    String message() default "Should be a valid password";

    /**
     * Specifies the validation groups this constraint belongs to.
     * <p>
     * Validation groups allow you to organize and control which constraints are applied
     * during specific validation processes. This is useful for selectively applying
     * constraints based on the context of the validation.
     * </p>
     *
     * @return the groups associated with this constraint
     */
    Class<?>[] groups() default {};

    /**
     * Provides additional metadata for the constraint.
     * <p>
     * This metadata can be used by the validation framework or custom logic to
     * provide extra context about the validation failure.
     * </p>
     * <p>
     * By default, this returns an empty array, meaning no additional metadata
     * is associated with the constraint.
     * </p>
     *
     * @return the payload associated with the constraint
     */
    Class<? extends Payload>[] payload() default {};
}
