package org.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void test_login() {
        assertFalse(login.login("", ""));
    }
}
