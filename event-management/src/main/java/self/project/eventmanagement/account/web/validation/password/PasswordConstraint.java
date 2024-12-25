package self.project.eventmanagement.account.web.validation.password;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PasswordConstraint {

    /**
     * This regular expression enforces the following password requirements:
     * <ul>
     *   <li>^(?=.*[a-z]): Ensures at least one lowercase letter.</li>
     *   <li>(?=.*[A-Z]): Ensures at least one uppercase letter.</li>
     *   <li>(?=.*\d): Ensures at least one digit.</li>
     * </ul>
     */
    LENIENT("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)$", ""),

    /**
     * This regular expression enforces the following password requirements:
     * <ul>
     *   <li>^(?=.*[a-z]): Ensures at least one lowercase letter.</li>
     *   <li>(?=.*[A-Z]): Ensures at least one uppercase letter.</li>
     *   <li>(?=.*\d): Ensures at least one digit.</li>
     *   <li>(?=.*[!@#$%^&*(),.?":{}|<>]): Ensures at least one special character.</li>
     * </ul>
     */
    STRONG("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])$", "");

    private final String constraintsRegex;

    private final String constraintViolationMessage;
}
