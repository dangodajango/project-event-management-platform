package self.project.eventmanagement.account.web.validation.email;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = OptionalEmailValidator.class)
public @interface OptionalEmail {

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
    String message() default "Should be a valid email";

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
    Class<?>[] groups() default {}; // Allows grouping of constraints

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
