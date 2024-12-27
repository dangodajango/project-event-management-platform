package self.project.eventmanagement.account.web.model.request;

import jakarta.validation.constraints.NotBlank;
import self.project.eventmanagement.account.web.validation.common.OptionalNotBlank;
import self.project.eventmanagement.account.web.validation.email.required.RequiredEmail;
import self.project.eventmanagement.account.web.validation.password.required.RequiredPassword;

import static self.project.eventmanagement.account.web.validation.password.PasswordConstraint.STRONG;

public record CreateAccountRequest(

        @NotBlank(message = "The name should be present and not blank")
        String name,

        @RequiredEmail(message = "The email should be present and valid")
        String email,

        @OptionalNotBlank
        String profilePictureUrl,

        @NotBlank(message = "The password should be present and not blank")
        @RequiredPassword(minLength = 10, maxLength = 17, passwordConstraint = STRONG)
        String password
) {
}
