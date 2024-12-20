package self.project.encryptor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MainTest {

    @Mock
    private Encryptor encryptor;

    @InjectMocks
    private Main main;

    @Test
    void testCommandLineRunnerShouldEncryptAllPassed() {
        // Given
        String passwordForEncryption = "secret-password";
        String userForEncryption = "secret-user";

        // When
        main.run(passwordForEncryption, userForEncryption);

        // Then
        verify(encryptor, times(1)).encrypt(passwordForEncryption);
        verify(encryptor, times(1)).encrypt(userForEncryption);
    }
}