package self.project.eventmanagement.account.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import self.project.eventmanagement.account.web.model.request.CreateAccountRequest;

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
    void should_return_bad_request_when_creating_account_with_invalid_email() {
        // Given

        // When

        // Then
    }

    @Test
    void should_return_bad_request_when_creating_account_with_invalid_profile_picture_url() {
        // Given

        // When

        // Then
    }

    @Test
    void should_return_bad_request_when_creating_account_with_invalid_password() {
        // Given

        // When

        // Then
    }
}