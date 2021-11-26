package org.user;

import org.rights.UserRights;

/**
 * @author pilzhere
 * @created 18/11/2021 - 2:58 PM
 * @project TDD_Athentication_and_Authorization_project
 */
public class User {
    private String userName;
    private String password;
    private String token;

    private UserRights accountRights;
    private UserRights provisionRights;

    public User (String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User (String userName, String password, UserRights accountRights, UserRights provisionRights) {
        this.userName = userName;
        this.password = password;
        this.accountRights = accountRights;
        this.provisionRights = provisionRights;
    }

    public String getUserName () {
        return userName;
    }

    public String getPassword () {
        return password;
    }

    public String getToken () {
        return token;
    }

    public void setToken (String token) {
        this.token = token;
    }

    public UserRights getAccountRights () {
        return accountRights;
    }

    public UserRights getProvisionRights () {
        return provisionRights;
    }
}
