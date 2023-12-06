package com.nephew.ss.ntsecurityservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nephew.ss.ntsecurityservice.config.JwtService;
import com.nephew.ss.ntsecurityservice.controllers.Action;
import com.nephew.ss.ntsecurityservice.controllers.AuthenticationRequest;
import com.nephew.ss.ntsecurityservice.controllers.AuthenticationService;
import com.nephew.ss.ntsecurityservice.controllers.RegisterRequest;
import com.nephew.ss.ntsecurityservice.controllers.Token;
import com.nephew.ss.ntsecurityservice.entities.AuthUser;
import com.nephew.ss.ntsecurityservice.entities.AuthUserRepository;
import com.nephew.ss.ntsecurityservice.entities.Role;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = "eureka.client.enabled=false")
class NtSecurityServiceApplicationTests {

	@Autowired
	private AuthUserRepository authUserRepository;
	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private JwtService jwtService;

	private final String TEST_FIRST_NAME = "Test First Name";
	private final String TEST_LAST_NAME = "Test Last Name";
	private final String TEST_ADMIN_EMAIL = "test_admin@mail.com";
	private final String TEST_USER_EMAIL = "test_user@mail.com";
	private final String TEST_USER_PASSWORD = "user_password";
	private final String TEST_ADMIN_PASSWORD = "admin_password";
	private final String TEST_SERVICE = "test-service";

	@Order(1)
	@Test
	void init() {
		Optional<AuthUser> user = authUserRepository.findByEmail(TEST_USER_EMAIL);
		Optional<AuthUser> admin = authUserRepository.findByEmail(TEST_ADMIN_EMAIL);
		if (!user.isPresent()) {
			authenticationService.registerUser(new RegisterRequest(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USER_EMAIL,
					TEST_USER_PASSWORD, TEST_SERVICE));
		}
		if (!admin.isPresent()) {
			authenticationService.registerAdmin(new RegisterRequest(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_ADMIN_EMAIL,
					TEST_ADMIN_PASSWORD, TEST_SERVICE));
		}
	}

	@Order(100)
	@Test
	void testAdminShouldExist() {
		Boolean exists = authUserRepository.findByEmail(TEST_ADMIN_EMAIL).isPresent();
		assertTrue(exists);
	}

	@Order(101)
	@Test
	void testUserShouldExist() {
		Boolean exists = authUserRepository.findByEmail(TEST_USER_EMAIL).isPresent();
		assertTrue(exists);
	}

	@Order(102)
	@Test
	void testUserIdShouldNotBeNull() {
		AuthUser user = authUserRepository.findByEmail(TEST_USER_EMAIL).get();
		assertNotNull(user.getId());
	}

	@Order(300)
	@Test
	void testSavedPasswordEncryption() {
		AuthUser user = authUserRepository.findByEmail(TEST_USER_EMAIL).get();
		assertNotEquals(TEST_USER_PASSWORD, user.getPassword()); // An unencrypted password should not equal the encrypted password. 
	}

	@Order(301)
	@Test
	void testTokenNotNull() {
		AuthUser user = authUserRepository.findByEmail(TEST_USER_EMAIL).get();
		String token = jwtService.generateToken(user);
		assertNotNull(token);
	} // isTokenValid

	@Order(302)
	@Test
	void testTokenIsValid() {
		AuthUser user = authUserRepository.findByEmail(TEST_USER_EMAIL).get();
		String token = jwtService.generateToken(user);
		assertTrue(jwtService.isTokenValid(token, user));
	}
	
	@Order(400)
	@Test
	void testTokenIsGenerated() {
		AuthenticationRequest request = new AuthenticationRequest(TEST_ADMIN_EMAIL, TEST_ADMIN_PASSWORD);
		Token token = authenticationService.generateToken(request);
		assertTrue(token.getToken().length() > 10);
	}
	
	@Order(401)
	@Test
	void testTokenIsValidFromAuthService() {
		AuthenticationRequest request = new AuthenticationRequest(TEST_ADMIN_EMAIL, TEST_ADMIN_PASSWORD);
		Token token = authenticationService.generateToken(request);
		Boolean tokenIsValid = authenticationService.validateToken(token, new Action(Role.ADMIN));
		assertTrue(tokenIsValid);
	}

	@Order(901)
	@Test
	void testAuthUserInstantiation() {
		AuthUser user = new AuthUser(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_ADMIN_EMAIL, TEST_USER_PASSWORD,
				TEST_SERVICE, Role.ADMIN);
		assertEquals(TEST_FIRST_NAME, user.getFirstName());
		assertEquals(TEST_LAST_NAME, user.getLastName());
		assertEquals(TEST_ADMIN_EMAIL, user.getEmail());
		assertEquals(TEST_USER_PASSWORD, user.getPassword());
		assertEquals(TEST_SERVICE, user.getServiceName());
		assertEquals(Role.ADMIN, user.getRole());
	}

	@Order(902)
	@Test
	void testRegisterRequestInstantiation() {
		RegisterRequest request = new RegisterRequest(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_ADMIN_EMAIL,
				TEST_USER_PASSWORD, TEST_SERVICE);
		assertEquals(TEST_FIRST_NAME, request.getFirstName());
		assertEquals(TEST_LAST_NAME, request.getLastName());
		assertEquals(TEST_ADMIN_EMAIL, request.getEmail());
		assertEquals(TEST_USER_PASSWORD, request.getPassword());
		assertEquals(TEST_SERVICE, request.getServiceName());
	}

	@Order(903)
	@Test
	void testAuthenticationRequestInstantiation() {
		AuthenticationRequest request = new AuthenticationRequest(TEST_ADMIN_EMAIL, TEST_ADMIN_PASSWORD);
		assertEquals(TEST_ADMIN_EMAIL, request.getEmail());
		assertEquals(TEST_ADMIN_PASSWORD, request.getPassword());
	}

	@Order(1000)
	@Test
	void cleanup() {
		Optional<AuthUser> user = authUserRepository.findByEmail(TEST_USER_EMAIL);
		Optional<AuthUser> admin = authUserRepository.findByEmail(TEST_ADMIN_EMAIL);
		if (user.isPresent()) {
			authUserRepository.delete(user.get());
			; // Delete test data
		}
		if (admin.isPresent()) {
			authUserRepository.delete(admin.get()); // Delete test data
		}
	}

}