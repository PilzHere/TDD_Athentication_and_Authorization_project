package org.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.token.TokenVerifier;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.login.utils.PasswordUtils.generateNewHashedPassword;

/**
 * @author pilzhere
 * @created 18/11/2021 - 2:50 PM
 * @project TDD_Athentication_and_Authorization_project
 */
public class LoginTest {
    private Login login;

    static Stream<Arguments> testDataUsers() {
        return Stream.of(
                Arguments.of("anna", "losen"),
                Arguments.of("berit", "123456"),
                Arguments.of("kalle", "password")
        );
    }

    @BeforeEach
    void setUp () {
        login = new Login();

        login.addUser("anna", "losen");
        login.addUser("berit", "123456");
        login.addUser("kalle", "password");
    }

    @DisplayName ("test_login_user_success successful")
    @ParameterizedTest
    @MethodSource ("testDataUsers")
    void test_login_user_success (String userName, String password) {
        assertDoesNotThrow(() -> login.loginUser(userName, password));
    }

    @DisplayName ("test_verify_token_success successful")
    @ParameterizedTest
    @MethodSource ("testDataUsers")
    void test_verify_token_success (String userName, String password) throws Exception {
        assertTrue(TokenVerifier.verifyToken(login.loginUser(userName, password)));
    }

    @DisplayName ("test_generate_new_hashed_password_success successful")
    @Test
    void test_generate_new_hashed_password_success () {
        assertTrue(generateNewHashedPassword("losen"));
    }
}
