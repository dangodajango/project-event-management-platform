package self.project.eventmanagement.account.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import self.project.eventmanagement.account.web.model.request.CreateAccountRequest;
import self.project.eventmanagement.account.web.model.request.UpdateAccountRequest;
import self.project.eventmanagement.common.web.response.RequestValidationFailureResponse;
import self.project.eventmanagement.util.TimestampResultMatcher;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static self.project.eventmanagement.account.web.AccountControllerTest.StaticTestData.*;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    private static final String ROOT_ENDPOINT_PATH = "/accounts";

    private static final int BAD_REQUEST_STATUS_CODE = 400;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("getInvalidAccountCreationData")
    void test_should_return_bad_request_when_creating_account_with_invalid_field(InvalidAccountCreationTestData testData) throws Exception {
        // When / Then
        mockMvc.perform(post(ROOT_ENDPOINT_PATH)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testData.createAccountRequest()))
        ).andExpectAll(
                status().isBadRequest(),
                content().contentType(APPLICATION_JSON),
                new TimestampResultMatcher(),
                jsonPath("$.status").value(BAD_REQUEST_STATUS_CODE),
                jsonPath("$.errorDetails[0].field").value(testData.expectedErrorDetails().field()),
                jsonPath("$.errorDetails[0].errorForField").value(testData.expectedErrorDetails().errorForField())
        ).andDo(print());
    }

    @Test
    void test_should_return_bad_request_when_creating_account_with_multiple_invalid_fields() throws Exception {
        // Given
        CreateAccountRequest requestWithMultipleInvalidFields = new CreateAccountRequest(null, null, VALID_PROFILE_PICTURE_URL, VALID_PASSWORD);

        // When / Then
        mockMvc.perform(post(ROOT_ENDPOINT_PATH)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestWithMultipleInvalidFields))
        ).andExpectAll(
                status().isBadRequest(),
                content().contentType(APPLICATION_JSON),
                new TimestampResultMatcher(),
                jsonPath("$.status").value(BAD_REQUEST_STATUS_CODE),
                jsonPath("$.errorDetails", hasSize(2)),
                jsonPath("$.errorDetails[?(@.field == 'name')].errorForField").value(ERROR_MESSAGE_FOR_NULL_OR_BLANK_NAME),
                jsonPath("$.errorDetails[?(@.field == 'email')].errorForField").value(ERROR_MESSAGE_FOR_NULL_BLANK_OR_INVALID_EMAIL)
        ).andDo(print());
    }

    private static Stream<InvalidAccountCreationTestData> getInvalidAccountCreationData() {
        return Stream.of(
                new InvalidAccountCreationTestData(new CreateAccountRequest(null, VALID_EMAIL, VALID_PROFILE_PICTURE_URL, VALID_PASSWORD), new RequestValidationFailureResponse.ErrorDetails("name", ERROR_MESSAGE_FOR_NULL_OR_BLANK_NAME)),
                new InvalidAccountCreationTestData(new CreateAccountRequest(" ", VALID_EMAIL, VALID_PROFILE_PICTURE_URL, VALID_PASSWORD), new RequestValidationFailureResponse.ErrorDetails("name", ERROR_MESSAGE_FOR_NULL_OR_BLANK_NAME)),
                new InvalidAccountCreationTestData(new CreateAccountRequest(VALID_NAME, null, VALID_PROFILE_PICTURE_URL, VALID_PASSWORD), new RequestValidationFailureResponse.ErrorDetails("email", ERROR_MESSAGE_FOR_NULL_BLANK_OR_INVALID_EMAIL)),
                new InvalidAccountCreationTestData(new CreateAccountRequest(VALID_NAME, " ", VALID_PROFILE_PICTURE_URL, VALID_PASSWORD), new RequestValidationFailureResponse.ErrorDetails("email", ERROR_MESSAGE_FOR_NULL_BLANK_OR_INVALID_EMAIL)),
                new InvalidAccountCreationTestData(new CreateAccountRequest(VALID_NAME, "john.doe.com", VALID_PROFILE_PICTURE_URL, VALID_PASSWORD), new RequestValidationFailureResponse.ErrorDetails("email", ERROR_MESSAGE_FOR_NULL_BLANK_OR_INVALID_EMAIL)),
                new InvalidAccountCreationTestData(new CreateAccountRequest(VALID_NAME, VALID_EMAIL, " ", VALID_PASSWORD), new RequestValidationFailureResponse.ErrorDetails("profilePictureUrl", ERROR_MESSAGE_FOR_BLANK_PROFILE_PICTURE_URL)),
                new InvalidAccountCreationTestData(new CreateAccountRequest(VALID_NAME, VALID_EMAIL, VALID_PROFILE_PICTURE_URL, null), new RequestValidationFailureResponse.ErrorDetails("password", ERROR_MESSAGE_FOR_NULL_OR_BLANK_PASSWORD)),
                new InvalidAccountCreationTestData(new CreateAccountRequest(VALID_NAME, VALID_EMAIL, VALID_PROFILE_PICTURE_URL, " "), new RequestValidationFailureResponse.ErrorDetails("password", ERROR_MESSAGE_FOR_NULL_OR_BLANK_PASSWORD)),
                new InvalidAccountCreationTestData(new CreateAccountRequest(VALID_NAME, VALID_EMAIL, VALID_PROFILE_PICTURE_URL, "abcd"), new RequestValidationFailureResponse.ErrorDetails("password", ERROR_MESSAGE_FOR_SMALLER_THAN_ALLOWED_PASSWORD)),
                new InvalidAccountCreationTestData(new CreateAccountRequest(VALID_NAME, VALID_EMAIL, VALID_PROFILE_PICTURE_URL, "1234567891011121314"), new RequestValidationFailureResponse.ErrorDetails("password", ERROR_MESSAGE_FOR_BIGGER_THAN_ALLOWED_PASSWORD)),
                new InvalidAccountCreationTestData(new CreateAccountRequest(VALID_NAME, VALID_EMAIL, VALID_PROFILE_PICTURE_URL, "Abc123defg"), new RequestValidationFailureResponse.ErrorDetails("password", ERROR_MESSAGE_FOR_NON_COMPLIANT_PASSWORD)));
    }

    private record InvalidAccountCreationTestData(CreateAccountRequest createAccountRequest,
                                                  RequestValidationFailureResponse.ErrorDetails expectedErrorDetails) {
    }

    @ParameterizedTest
    @MethodSource("getInvalidAccountModificationData")
    void test_should_return_bad_request_when_updating_account_with_invalid_field(InvalidAccountModificationTestData testData) throws Exception {
        // Given
        Long accountId = 1L;

        // When / Then
        mockMvc.perform(put(ROOT_ENDPOINT_PATH + "/{id}", String.valueOf(accountId))
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testData.updateAccountRequest))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(print());
    }

    void test_should_return_bad_request_when_updating_account_with_multiple_invalid_fields() {

    }

    private static Stream<InvalidAccountModificationTestData> getInvalidAccountModificationData() {
        return Stream.of(
                new InvalidAccountModificationTestData(new UpdateAccountRequest(" ", VALID_EMAIL, VALID_PROFILE_PICTURE_URL, VALID_PASSWORD), new RequestValidationFailureResponse.ErrorDetails("name", ERROR_MESSAGE_FOR_BLANK_NAME)),
                new InvalidAccountModificationTestData(new UpdateAccountRequest(VALID_NAME, "john.doe.com", VALID_PROFILE_PICTURE_URL, VALID_PASSWORD), new RequestValidationFailureResponse.ErrorDetails("email", ERROR_MESSAGE_FOR_INVALID_EMAIL)),
                new InvalidAccountModificationTestData(new UpdateAccountRequest(VALID_NAME, VALID_EMAIL, " ", VALID_PASSWORD), new RequestValidationFailureResponse.ErrorDetails("profilePictureUrl", ERROR_MESSAGE_FOR_BLANK_PROFILE_PICTURE_URL)),
                new InvalidAccountModificationTestData(new UpdateAccountRequest(VALID_NAME, VALID_EMAIL, VALID_PROFILE_PICTURE_URL, "abcd"), new RequestValidationFailureResponse.ErrorDetails("password", ERROR_MESSAGE_FOR_SMALLER_THAN_ALLOWED_PASSWORD)),
                new InvalidAccountModificationTestData(new UpdateAccountRequest(VALID_NAME, VALID_EMAIL, VALID_PROFILE_PICTURE_URL, "1234567891011121314"), new RequestValidationFailureResponse.ErrorDetails("password", ERROR_MESSAGE_FOR_BIGGER_THAN_ALLOWED_PASSWORD)),
                new InvalidAccountModificationTestData(new UpdateAccountRequest(VALID_NAME, VALID_EMAIL, VALID_PROFILE_PICTURE_URL, "Abc123defg"), new RequestValidationFailureResponse.ErrorDetails("password", ERROR_MESSAGE_FOR_NON_COMPLIANT_PASSWORD)));
    }

    private record InvalidAccountModificationTestData(UpdateAccountRequest updateAccountRequest,
                                                      RequestValidationFailureResponse.ErrorDetails expectedErrorDetails) {
    }

    static class StaticTestData {

        public static final String VALID_NAME = "John Doe";

        public static final String VALID_EMAIL = "john.doe@gmail.com";

        public static final String VALID_PROFILE_PICTURE_URL = "/url/to/profile/picture.jpg";

        public static final String VALID_PASSWORD = "Aa1@Str0ngPass!";

        public static final String ERROR_MESSAGE_FOR_NULL_OR_BLANK_NAME = "The name should be present and not blank";

        public static final String ERROR_MESSAGE_FOR_BLANK_NAME = "The name should not be blank";

        public static final String ERROR_MESSAGE_FOR_NULL_BLANK_OR_INVALID_EMAIL = "The email should be present and valid";

        public static final String ERROR_MESSAGE_FOR_INVALID_EMAIL = "Should be a valid email";

        public static final String ERROR_MESSAGE_FOR_BLANK_PROFILE_PICTURE_URL = "The profile picture url should not be blank if present";

        public static final String ERROR_MESSAGE_FOR_NULL_OR_BLANK_PASSWORD = "The password should be present and not blank";

        public static final String ERROR_MESSAGE_FOR_SMALLER_THAN_ALLOWED_PASSWORD = "The password must be at least 10 characters long";

        public static final String ERROR_MESSAGE_FOR_BIGGER_THAN_ALLOWED_PASSWORD = "The password must be at most 17 characters long";

        public static final String ERROR_MESSAGE_FOR_NON_COMPLIANT_PASSWORD = "Password must contain at least one lowercase letter, at least one uppercase letter, at least one digit, at least one special character";
    }
}