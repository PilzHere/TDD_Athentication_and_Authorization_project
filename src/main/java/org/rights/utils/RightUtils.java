package org.rights.utils;

import com.auth0.jwt.JWT;
import org.resource.Resource;
import org.rights.UserRights;
import org.user.User;

import java.util.List;

/**
 * @author pilzhere
 * @created 24/11/2021 - 9:18 PM
 * @project TDD_Athentication_and_Authorization_project
 */
public class RightUtils {
    private static List<User> users;

    public static void setUsers (List<User> users) {
        RightUtils.users = users;
    }

    public static UserRights getUserRights(String token, Resource resource) {
        String userName = JWT.decode(token).getClaim("name").asString();

        for (User user: users) {
            if (user.getUserName().equals(userName)) {
                System.out.print(user.getUserName());
                switch (resource) {
                    case ACCOUNT:
                        System.out.println(" | " + resource + " | " + user.getAccountRights());
                        return user.getAccountRights();
                    case PROVISION_CALC:
                        System.out.println(" | " + resource + " | " + user.getProvisionRights());
                        return user.getProvisionRights();
                }
            }
        }

        return null;
    }
}
