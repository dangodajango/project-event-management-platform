package self.project.encryptor;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Provides a mechanism for encrypting sensitive information to enhance security within the application.
 * <p>
 * Instead of storing sensitive information in plaintext within configuration files,
 * the Jasypt library (Java Simplified Encryption) allows data to be encrypted, stored as scrambled text,
 * and automatically decrypted at runtime.
 * </p>
 * <p>
 * By leveraging the Spring Boot extension `jasypt-spring-boot-starter`,
 * this integration supports application properties seamlessly. When a Jasypt-encrypted string is
 * stored in an `ENC()` placeholder within the application properties, it will be decrypted
 * automatically when accessed. This ensures that sensitive data is protected and reduces the risk
 * of exposing plain text information in the codebase.
 * </p>
 */
@Component
public class Encryptor {

    /**
     * The default encryptor used by the `jasypt-spring-boot-starter` library.
     * <p>
     * This encryptor is utilized by Spring Boot to decrypt properties wrapped in the `ENC()` placeholder.
     * To prevent mismatches between encryption configurations such as algorithm, iteration count, or encoding,
     * data is encrypted using the same encryptor. This ensures compatibility at runtime and avoids decryption issues.
     * </p>
     */
    @Qualifier("lazyJasyptStringEncryptor")
    private final StringEncryptor stringEncryptor;

    public Encryptor(StringEncryptor encryptor) {
        this.stringEncryptor = encryptor;
    }

    /**
     * A symmetric-key encryptor that uses a password for both encryption and decryption processes.
     * <p>
     * The password, which acts as the encryption key, must be configured during the bean creation
     * of the `lazyJasyptStringEncryptor`. It should be supplied through a property defined in
     * the `application.properties` file.
     * </p>
     */
    public String encrypt(String data) {
        return stringEncryptor.encrypt(data);
    }
}
