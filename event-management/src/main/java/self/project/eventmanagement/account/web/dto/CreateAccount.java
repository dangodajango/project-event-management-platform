package self.project.eventmanagement.account.web.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import self.project.eventmanagement.account.web.validation.password.Password;

import static self.project.eventmanagement.account.web.validation.password.PasswordConstraint.STRONG;

public record CreateAccount(

        @NotBlank(message = "The name should be present and not blank")
        String name,

        @NotBlank(message = "The email should be present and not blank")
        @Email(message = "Should be a valid email")
        String email,

        @Nullable
        String profilePictureUrl,

        @NotBlank(message = "The password should be present and not blank")
        @Password(minLength = 10, maxLength = 17, passwordConstraint = STRONG)
        String password
) {
}
