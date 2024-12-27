package self.project.eventmanagement.account.web;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import self.project.eventmanagement.account.web.model.request.CreateAccountRequest;
import self.project.eventmanagement.account.web.model.request.UpdateAccountRequest;
import self.project.eventmanagement.account.web.model.response.RequestValidationFailureResponse;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RequestMapping("/accounts")
@RestController
public class AccountController {

    @PostMapping
    public void create(@Valid @RequestBody CreateAccountRequest createAccountRequest) {

    }

    @PutMapping("/${id}")
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateAccountRequest updateAccountRequest) {

    }

    @ExceptionHandler(exception = MethodArgumentNotValidException.class)
    public ResponseEntity<RequestValidationFailureResponse> handleValidationException(MethodArgumentNotValidException exception) {
        RequestValidationFailureResponse response = new RequestValidationFailureResponse(LocalDateTime.now(), BAD_REQUEST.value(), exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
