package org.login;

import org.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pilzhere
 * @created 18/11/2021 - 2:56 PM
 * @project TDD_Athentication_and_Authorization_project
 */
public class Login {
    private final List<User> users = new ArrayList<>();

    public Login () { }

    public void addUser(String userName, String password) {
        users.add(new User(userName, password));
    }

    public boolean loginUser (String userName, String password) {
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) return true;
        }

        return false;
    }
}
