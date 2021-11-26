package org.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author pilzhere
 * @created 18/11/2021 - 2:50 PM
 * @project TDD_Athentication_and_Authorization_project
 */
public class LoginTest {
    Login login;

    static Stream<Arguments> testDataUsers () {
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

    @DisplayName ("test_login_success")
    @ParameterizedTest
    @MethodSource ("testDataUsers")
    void test_login (String username, String password) {
        assertTrue(login.login(username, password));
    }
}
