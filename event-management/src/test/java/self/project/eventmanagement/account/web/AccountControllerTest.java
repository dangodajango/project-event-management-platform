package self.project.eventmanagement.account.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import self.project.eventmanagement.account.web.model.request.CreateAccountRequest;

import java.util.stream.Stream;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static self.project.eventmanagement.account.web.ValidAccountData.*;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    private static final String ROOT_ENDPOINT_PATH = "/accounts";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_bad_request_when_creating_account_with_invalid_name() throws Exception {
        // Given
        String invalidName = " ";
        CreateAccountRequest createAccountRequest = new CreateAccountRequest(invalidName, EMAIL, PROFILE_PICTURE_URL, PASSWORD);


        // When / Then
        mockMvc.perform(post(ROOT_ENDPOINT_PATH)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createAccountRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andDo(print());
    }

    @Test
    void should_return_bad_request_when_creating_account_with_invalid_email() throws Exception {
        // Given
        String invalidEmail = "";
        CreateAccountRequest createAccountRequest = new CreateAccountRequest(NAME, invalidEmail, PROFILE_PICTURE_URL, PASSWORD);

        // When / Then
        mockMvc.perform(post(ROOT_ENDPOINT_PATH)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createAccountRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andDo(print());
    }

    @Test
    void should_return_bad_request_when_creating_account_with_invalid_profile_picture_url() throws Exception {
        // Given
        String invalidProfilePicture = " ";
        CreateAccountRequest createAccountRequest = new CreateAccountRequest(NAME, EMAIL, invalidProfilePicture, PASSWORD);

        // When / Then
        mockMvc.perform(post(ROOT_ENDPOINT_PATH)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createAccountRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andDo(print());
    }

    @Test
    void should_return_bad_request_when_creating_account_with_invalid_password() throws Exception {
        // Given
        String invalidPassword = "";
        CreateAccountRequest createAccountRequest = new CreateAccountRequest(NAME, EMAIL, PROFILE_PICTURE_URL, invalidPassword);

        // When / Then
        mockMvc.perform(post(ROOT_ENDPOINT_PATH)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createAccountRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andDo(print());
    }

    @ParameterizedTest
    @MethodSource("getInvalidAccountCreationData")
    void test_should_return_bad_request_when_creating_account_with_invalid_field(InvalidAccountTestData testData) throws Exception {
        // When / Then
        mockMvc.perform(post(ROOT_ENDPOINT_PATH)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testData.createAccountRequest())))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andDo(print());
    }

    @Test
    void test_should_return_bad_request_when_creating_account_with_multiple_invalid_fields() {

    }

    private static Stream<InvalidAccountTestData> getInvalidAccountCreationData() {
        return Stream.of(
                new InvalidAccountTestData(new CreateAccountRequest(null, EMAIL, PROFILE_PICTURE_URL, PASSWORD), ""),
                new InvalidAccountTestData(new CreateAccountRequest(" ", EMAIL, PROFILE_PICTURE_URL, PASSWORD), ""),
                new InvalidAccountTestData(new CreateAccountRequest(NAME, null, PROFILE_PICTURE_URL, PASSWORD), ""),
                new InvalidAccountTestData(new CreateAccountRequest(NAME, " ", PROFILE_PICTURE_URL, PASSWORD), ""),
                new InvalidAccountTestData(new CreateAccountRequest(NAME, "john.doe.com", PROFILE_PICTURE_URL, PASSWORD), ""),
                new InvalidAccountTestData(new CreateAccountRequest(NAME, EMAIL, " ", PASSWORD), ""),
                new InvalidAccountTestData(new CreateAccountRequest(NAME, EMAIL, PROFILE_PICTURE_URL, null), ""),
                new InvalidAccountTestData(new CreateAccountRequest(NAME, EMAIL, PROFILE_PICTURE_URL, " "), ""),
                new InvalidAccountTestData(new CreateAccountRequest(NAME, EMAIL, PROFILE_PICTURE_URL, "abcd"), ""),
                new InvalidAccountTestData(new CreateAccountRequest(NAME, EMAIL, PROFILE_PICTURE_URL, "1234567891011121314"), ""),
                new InvalidAccountTestData(new CreateAccountRequest(NAME, EMAIL, PROFILE_PICTURE_URL, "Abc123defg"), "")
        );
    }
}

record InvalidAccountTestData(CreateAccountRequest createAccountRequest, String expectedErrorMessage) {

}