package org.login;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pilzhere
 * @created 18/11/2021 - 2:56 PM
 * @project TDD_Athentication_and_Authorization_project
 */
public class Login {
    private final List<User> users = new ArrayList<>();

    public Login () {
        users.add(new User("anna", "losen"));
        users.add(new User("berit", "123456"));
        users.add(new User("kalle", "password"));
    }

    public boolean loginUser (String userName, String password) {
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) return true;
        }

        return false;
    }
}
