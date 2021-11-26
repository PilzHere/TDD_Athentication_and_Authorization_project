package org.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @author pilzhere
 * @created 19/11/2021 - 12:05 PM
 * @project TDD_Athentication_and_Authorization_project
 */
public class TokenVerifier {
    public static boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            if (jwt.getToken() != null && token.equals(jwt.getToken()))
                return true;

        } catch (JWTVerificationException exception){
            //Invalid signature/claims
            exception.printStackTrace();
        }

        return false;
    }
}
