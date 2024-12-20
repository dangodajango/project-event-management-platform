package self.project.encryptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Encrypts all command line arguments passed during application startup.
 * <p>
 * The encrypted data can be used wherever the default decryptor from the
 * `jasypt-spring-boot-starter` library is applied.
 * </p>
 * <p>
 * Ensure that an encryption password is provided in the `application.properties` file for proper encryption and decryption.
 * </p>
 */
@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Main implements CommandLineRunner {

    private final Encryptor encryptor;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... dataForEncryption) {
        for (String data : dataForEncryption) {
            try {
                String encryptedData = encryptor.encrypt(data);
                log.info("{} -> {}", data, encryptedData);
            } catch (Exception exception) {
                log.error("Error occurred while encrypting {}", dataForEncryption, exception);
            }
        }
    }
}
