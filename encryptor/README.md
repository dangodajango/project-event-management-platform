# Encryptor

This application provides an encryption and decryption mechanism for sensitive data using Jasypt (Java Simplified
Encryption). It encrypts sensitive information, such as command line arguments, to prevent exposing plain text data in
the codebase or logs.

## Prerequisites

- **Java 17**: Ensure that you have Java 17 installed on your system.

## Setup

1. **Clone the Repository** (or download the source code).


2. **Build the Application**:
    - To package the application into a JAR file, run the following command:
    ```bash
    mvn clean package
    ```
   This will generate the `encryptor.jar` file in the `target/` directory.


3. **Configure the Password**:
   - Add the password to your `application.properties` file for encryption and decryption. **Do not commit** this file with the password in it for security reasons.
    ```properties
    jasypt.encryptor.password=your_encryption_password
    ```
   - Alternatively, you can export the password as an environment variable that matches the key in `application.properties`. For example:
    ```bash
    export ENCRYPTOR_PASSWORD=your_encryption_password
    ```
   This password acts as a symmetric key and must be kept confidential to ensure the security of the encrypted data.


4. **Encrypting Data**:
    - To encrypt command line arguments, run the packaged application using the following command:
    ```bash
    java -jar encryptor.jar <arguments_to_encrypt>
    ```
   The application will encrypt the provided arguments and display the encrypted data.


5. **Decryption**:
    - The encrypted data can be used anywhere in your application where the default decryptor
      from `jasypt-spring-boot-starter` is applied. The data will be automatically decrypted at runtime as long as the
      correct encryption password is provided in the `application.properties`.

## Security Note

- The encryption password is critical for both encryption and decryption processes. Make sure to keep it secure and
  avoid exposing it in plaintext.
- Inject the password securely via an environment variable or another secure method, especially in production
  environments.
