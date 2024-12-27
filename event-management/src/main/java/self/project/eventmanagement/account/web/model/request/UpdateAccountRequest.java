package self.project.eventmanagement.account.web.model.request;

import self.project.eventmanagement.account.web.validation.email.optional.OptionalEmail;
import self.project.eventmanagement.account.web.validation.password.optional.OptionalPassword;
import self.project.eventmanagement.common.web.validation.OptionalNotBlank;

import static self.project.eventmanagement.account.web.validation.password.PasswordConstraint.STRONG;

public record UpdateAccountRequest(

        @OptionalNotBlank(message = "The name should not be blank")
        String name,

        @OptionalEmail
        String email,

        @OptionalNotBlank(message = "The profile picture url should not be blank if present")
        String profilePictureUrl,

        @OptionalPassword(minLength = 10, maxLength = 17, passwordConstraint = STRONG)
        String password
) {
}
