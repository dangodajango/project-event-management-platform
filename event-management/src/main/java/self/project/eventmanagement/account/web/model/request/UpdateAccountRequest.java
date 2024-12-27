package self.project.eventmanagement.account.web.model.request;

import self.project.eventmanagement.account.web.validation.common.OptionalNotBlank;
import self.project.eventmanagement.account.web.validation.email.OptionalEmail;
import self.project.eventmanagement.account.web.validation.password.OptionalPassword;

import static self.project.eventmanagement.account.web.validation.password.PasswordConstraint.STRONG;

public record UpdateAccountRequest(

        @OptionalNotBlank(message = "The name should not be blank")
        String name,

        @OptionalEmail
        String email,

        @OptionalNotBlank(message = "The profile picture url should not be blank")
        String profilePictureUrl,

        @OptionalPassword(minLength = 10, maxLength = 17, passwordConstraint = STRONG)
        String password
) {
}
