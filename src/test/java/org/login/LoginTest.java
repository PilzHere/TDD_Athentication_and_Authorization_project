package org.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.login.utils.RightUtils;
import org.token.TokenVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.login.utils.PasswordUtils.generateNewHashedPassword;

/**
 * @author pilzhere
 * @created 18/11/2021 - 2:50 PM
 * @project TDD_Athentication_and_Authorization_project
 */
public class LoginTest {
    private Login login;

    @BeforeEach
    void setUp () {
        login = new Login();
    }

    @DisplayName ("test_login_user_success successful")
    @Test
    void test_login_user_success () {
        String userName = "berit", password = "123456";
        assertDoesNotThrow(() -> login.loginUser(userName, password));
    }

    @DisplayName ("test_verify_token_success successful")
    @Test
    void test_verify_token_success () throws Exception {
        String userName = "berit", password = "123456";
        assertTrue(TokenVerifier.verifyToken(login.loginUser(userName, password)));
    }

    @DisplayName ("test_generate_new_hashed_password_success successful")
    @Test
    void test_generate_new_hashed_password_success () {
        assertTrue(generateNewHashedPassword("losen"));
    }

    @DisplayName ("test_get_user_rights successful")
    @Test
    void test_get_user_rights_success () throws Exception {
        login.giveTokensToAllUsers();

        for (User user: login.getUsers()) {
            assertNotNull(RightUtils.getUserRights(user.getToken(), Resource.ACCOUNT));
        }

        for (User user: login.getUsers()) {
            assertNotNull(RightUtils.getUserRights(user.getToken(), Resource.PROVISION_CALC));
        }
    }
}
