package org.login;

import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.login.utils.PasswordUtils;
import org.resource.Resource;
import org.rights.utils.RightUtils;
import org.token.TokenVerifier;
import org.user.User;
import org.rights.UserRights;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.login.utils.PasswordUtils.generateNewHashedPassword;

/**
 * @author pilzhere
 * @created 18/11/2021 - 2:50 PM
 * @project TDD_Athentication_and_Authorization_project
 */

@TestMethodOrder (MethodOrderer.OrderAnnotation.class)
public class LoginTest {
    private Login login;

    static Stream<Arguments> testDataUsers () {
        return Stream.of(
                Arguments.of("anna", "losen", UserRights.READ, UserRights.READ_WRITE_EXECUTE),
                Arguments.of("berit", "123456", UserRights.READ, UserRights.WRITE),
                Arguments.of("kalle", "password", UserRights.WRITE, UserRights.WRITE_EXECUTE)
        );
    }

    @BeforeEach
    void setUp () {
        login = new Login();

        login.addUser("anna", "losen", UserRights.READ, UserRights.READ_WRITE_EXECUTE);
        login.addUser("berit", "123456", UserRights.READ, UserRights.WRITE);
        login.addUser("kalle", "password", UserRights.WRITE, UserRights.WRITE_EXECUTE);

        login.updateUsersRightUtils();
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

    // delete from here
    @Test
    @DisplayName ("warmup ")
    @Order (1)
    void warmup () {
        for (int i = 0; i < 500; i++) {
            final String salt = PasswordUtils.generateSalt(512).get();
            assertNotNull(PasswordUtils.hashPassword512(Integer.toString(i), salt));
        }
    }

    @Test
    @DisplayName ("test_verify_token_success_HMAC256_SUCCESSFUL")
    @Order (2)
    void test_verify_token_success_HMAC256 () {
        for (int i = 0; i < 500; i++) {
            final String salt = PasswordUtils.generateSalt(256).get();
            assertNotNull(PasswordUtils.hashPassword256(Integer.toString(i), salt));
        }
    }

    @Test
    @DisplayName ("test_verify_token_success_HMAC384_SUCCESSFUL")
    @Order (3)
    void test_verify_token_success_HMAC384 () {

        for (int i = 0; i < 500; i++) {
            final String salt = PasswordUtils.generateSalt(384).get();
            assertNotNull(PasswordUtils.hashPassword384(Integer.toString(i), salt));
        }
    }

    @Test
    @Order (4)
    @DisplayName ("test_verify_token_success_HMAC512_SUCCESSFUL")
    void test_verify_token_success_HMAC512 () {
        for (int i = 0; i < 500; i++) {
            final String salt = PasswordUtils.generateSalt(512).get();
            assertNotNull(PasswordUtils.hashPassword512(Integer.toString(i), salt));
        }
    }
    // delete end

    @DisplayName ("test_generate_new_hashed_password_success successful")
    @Test
    void test_generate_new_hashed_password_success () {
        assertNotNull(generateNewHashedPassword(login.getUsers().get(0).getSalt(), "losen"));
    }

    @DisplayName ("test_get_user_rights successful")
    @ParameterizedTest
    @MethodSource ("testDataUsers")
    void test_get_user_rights_success (String username, String password) throws Exception {
        //login.giveTokensToAllUsers();

        String token = login.loginUser(username, password);
        assertNotNull(RightUtils.getUserRights(token, Resource.ACCOUNT));
        assertNotNull(RightUtils.getUserRights(token, Resource.PROVISION_CALC));
    }
}
