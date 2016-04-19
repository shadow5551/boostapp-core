package resources.infrastructure;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import resources.users.User;
import resources.users.UserService;

import java.util.Map;

public class Auth {
    public static User getCurrentUser() {
        Map session = ActionContext.getContext().getSession();

        if (session.get("email") != null) {
            return UserService.getByEmail(session.get("email").toString());
        } else {
            return new User();
        }
    }

    public static void setCurrentUser(SessionMap session, String email) {
        session.put("email", email);
    }

    public static void unsetCurrentUser(SessionMap session) {
        session.remove("email");
    }
}
