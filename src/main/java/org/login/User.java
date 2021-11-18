package org.login;

/**
 * @author pilzhere
 * @created 18/11/2021 - 2:58 PM
 * @project TDD_Athentication_and_Authorization_project
 */
public class User {
    private String userName;
    private String password;

    public User (String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName () {
        return userName;
    }

    public String getPassword () {
        return password;
    }
}
