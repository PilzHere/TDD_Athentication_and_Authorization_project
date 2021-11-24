package org.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.security.interfaces.RSAPublicKey;
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

    public String loginUser (String userName, String password) throws Exception {
            for (User user : users) {
                if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                    try {
                        final Algorithm algorithm = Algorithm.HMAC256("secret");
                        final String token = JWT.create().withIssuer("auth0").withClaim("name", userName).sign(algorithm);
                        return token;
                    } catch (JWTCreationException e) {
                        e.printStackTrace();
                    }
                }
            }

            // User does not exist.
            throw new Exception("User does not exist!");
    }
}
