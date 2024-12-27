package self.project.eventmanagement.common.web.response;

import java.time.LocalDateTime;
import java.util.List;

public record RequestValidationFailureResponse(LocalDateTime timestamp, Integer status,
                                               List<ErrorDetails> errorDetails) {

    public record ErrorDetails(String field, String errorForField) {
    }
}
