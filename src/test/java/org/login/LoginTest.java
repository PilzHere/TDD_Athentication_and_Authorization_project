package org.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.login.utils.PasswordUtils.generateNewHashedPassword;

/**
 * @author pilzhere
 * @created 18/11/2021 - 2:50 PM
 * @project TDD_Athentication_and_Authorization_project
 */
public class LoginTest {
    Login login;

    @BeforeEach
    void setUp () {
        login = new Login();
    }

    @Test
    void test_login_user_success () {
        assertTrue(login.loginUser("berit", "123456"));
    }

    @Test
    void test_generate_new_hashed_password_success () {
        assertTrue(generateNewHashedPassword("losen"));
    }
}
