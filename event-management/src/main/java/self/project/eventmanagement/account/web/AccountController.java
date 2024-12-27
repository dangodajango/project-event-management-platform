package self.project.eventmanagement.account.web;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import self.project.eventmanagement.account.web.model.request.CreateAccountRequest;
import self.project.eventmanagement.account.web.model.request.UpdateAccountRequest;
import self.project.eventmanagement.common.web.response.RequestValidationFailureResponse;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RequestMapping("/accounts")
@RestController
public class AccountController {

    @PostMapping
    public void create(@Valid @RequestBody CreateAccountRequest createAccountRequest) {

    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateAccountRequest updateAccountRequest) {

    }

    @ExceptionHandler(exception = MethodArgumentNotValidException.class)
    public ResponseEntity<RequestValidationFailureResponse> handleValidationException(MethodArgumentNotValidException exception) {
        List<RequestValidationFailureResponse.ErrorDetails> errorDetails = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new RequestValidationFailureResponse.ErrorDetails(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        RequestValidationFailureResponse response = new RequestValidationFailureResponse(LocalDateTime.now(), BAD_REQUEST.value(), errorDetails);
        return ResponseEntity.badRequest().body(response);
    }
}


