package org.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import org.login.utils.PasswordUtils;
import org.rights.utils.RightUtils;
import org.rights.UserRights;
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

    public void addUser (String username, String password, UserRights accountRights, UserRights provisionCalcRights) {
        final String salt = PasswordUtils.generateSalt(512).get();

        final String hashedPassword = PasswordUtils.generateNewHashedPassword(salt, password);
        users.add(new User(username, salt, hashedPassword, accountRights, provisionCalcRights));
    }

    public void updateUsersRightUtils() {
        RightUtils.setUsers(users);
    }

    public void giveTokensToAllUsers () throws Exception {
        for (User user : users) {
            user.setToken(loginUser(user.getUserName(), user.getPassword()));
            System.out.println(user.getToken());
        }
    }

    public String loginUser (String userName, String password) throws Exception {
            for (User user : users) {
                final boolean verified = PasswordUtils.verifyPassword(password, user.getPassword(), user.getSalt());
                if (user.getUserName().equals(userName) && verified) {
                    try {
                        final Algorithm algorithm = Algorithm.HMAC256("secret");
                        final String token = JWT.create().withIssuer("auth0").withClaim("name", userName).sign(algorithm);
                        String username = JWT.decode(token).getClaim("name").asString();
                        System.out.println("USERNAME: " + username);
                        return token;

                    } catch (JWTCreationException e) {
                        e.printStackTrace();
                    }
                }
            }

            // User does not exist.
            throw new Exception("User does not exist!");
    }

    // TEST
    public String loginUser (String userName, String password, final Algorithm algo) throws Exception {
        for (User user : users) {
            final boolean verified = PasswordUtils.verifyPassword(password, user.getPassword(), user.getSalt());
            if (user.getUserName().equals(userName) && verified) {
                try {
                    //final Algorithm algorithm = Algorithm.HMAC256("secret");
                    final String token = JWT.create().withIssuer("auth0").withClaim("name", userName).sign(algo);
                    return token;

                } catch (JWTCreationException e) {
                    e.printStackTrace();
                }
            }
        }

        // User does not exist.
        throw new Exception("User does not exist!");
    }

    public List<User> getUsers () {
        return users;
    }
}
