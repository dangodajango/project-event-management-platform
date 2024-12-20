package self.project.encryptor;

import com.ulisesbocchio.jasyptspringboot.configuration.EncryptablePropertyResolverConfiguration;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {Encryptor.class, EncryptablePropertyResolverConfiguration.class})
@ActiveProfiles("test")
class EncryptorTest {

    @Autowired
    private Encryptor encryptor;

    @Autowired
    private StringEncryptor lazyJasyptStringEncryptor;

    @Test
    void testPasswordIsEncryptedAndDecryptedCorrectly() {
        // Given
        String dataForEncryption = "my-very-secret-password";

        // When
        String encryptedPassword = encryptor.encrypt(dataForEncryption);

        // Then
        assertThat(encryptedPassword)
                .isNotBlank()
                .isNotEqualTo(dataForEncryption);
        String decryptedPassword = lazyJasyptStringEncryptor.decrypt(encryptedPassword);
        assertThat(decryptedPassword).isEqualTo(dataForEncryption);
    }
}