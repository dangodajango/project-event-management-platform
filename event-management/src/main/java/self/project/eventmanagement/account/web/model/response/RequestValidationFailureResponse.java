package self.project.eventmanagement.account.web.model.response;

import java.time.LocalDateTime;

public record RequestValidationFailureResponse(LocalDateTime timestamp, Integer status, String message) {
}
