Simple way to move to https for local development (non-prod). 

# Generate Keystore

1. **Generate a Private Key**:
   First, generate a private key using OpenSSL. You can use the following command:

   ```bash
   openssl genpkey -algorithm RSA -out key.pem -aes256
   ```

   This command generates a new RSA private key and encrypts it with AES 256-bit encryption. You will be prompted to enter a passphrase to protect the private key.

2. **Generate a CSR (Certificate Signing Request) (Optional)**:
   If you want to obtain a certificate from a Certificate Authority (CA), you need to generate a CSR. You can generate a CSR using the following command:

   ```bash
   openssl req -new -key key.pem -out csr.pem
   ```

   Follow the prompts to provide information about your organization, common name (domain name), etc.

3. **Self-sign the Certificate (Optional)**:
   If you want to create a self-signed certificate, you can use the following command:

   ```bash
   openssl req -x509 -days 365 -key key.pem -in csr.pem -out certificate.pem
   ```

   This command creates a self-signed certificate valid for 365 days.

4. **Combine Key and Certificate into PKCS12 Keystore**:
   Now, you can combine the private key and certificate into a PKCS12 keystore using the following command:

   ```bash
   openssl pkcs12 -export -out keystore.p12 -inkey key.pem -in certificate.pem
   ```

   You will be prompted to enter a passphrase for the PKCS12 keystore. This passphrase will protect the entire keystore.

5. **Provide Information**:
   You may be prompted to provide additional information such as an export password. This password will be required when accessing the PKCS12 keystore.

# Gateway Config

1. **Generate SSL Certificate**:
   You can either generate a self-signed certificate for development/testing purposes or obtain a certificate from a Certificate Authority (CA) for production use. For development, you can use tools like `keytool` or `openssl` to generate a self-signed certificate.

2. **Store SSL Certificate**:
   Store the SSL certificate and its private key in a keystore file. The keystore file is a repository of security certificates, including SSL certificates.

3. **Configure Spring Boot Application**:
   In your Spring Boot application, configure the SSL properties in `application.properties` or `application.yml`. Here's an example configuration:

   ```properties
   server.port=443
   server.ssl.key-store-type=PKCS12
   server.ssl.key-store=classpath:keystore.p12
   server.ssl.key-store-password=your_keystore_password
   server.ssl.key-alias=your_key_alias
   ```

   Replace `keystore.p12` with the path to your keystore file, and provide the keystore password and key alias accordingly.

4. **Redirect HTTP to HTTPS (Optional)**:
   It's a good practice to redirect HTTP requests to HTTPS for better security. You can do this by configuring your API Gateway to redirect HTTP requests to HTTPS. Here's an example of how you can do it in Spring Security:

   ```java
   @Configuration
   public class SecurityConfig extends WebSecurityConfigurerAdapter {
       @Override
       protected void configure(HttpSecurity http) throws Exception {
           http.requiresChannel().anyRequest().requiresSecure();
       }
   }
   ```

5. **Test SSL Configuration**:
   Restart your Spring Boot application and access your API Gateway using HTTPS (e.g., `https://yourdomain.com`). Ensure that the SSL connection is established successfully.