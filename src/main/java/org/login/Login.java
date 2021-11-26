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
    private List<User> users = new ArrayList<>();

    public Login () { }

    public void addUser(String username, String password) {
        users.add(new User(username, password));
    }

    public boolean login (String userName, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(userName) && users.get(i).getPassword().equals(password))
                return true;
        }

        return false;
    }
}
