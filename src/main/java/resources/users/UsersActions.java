package resources.users;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resources.infrastructure.Auth;
import resources.infrastructure.ValidateResult;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;

@Result(type = "json")
public class UsersActions extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 9037336567869476226L;
    private static final Logger log = LogManager.getLogger(UsersActions.class);

    private String email;
    private String password;
    private String repeatPassword;
    private User currentUser;
    private boolean signout;

    private SessionMap<String, Object> session;
    private List<Map<String, String>> validateErrors;
    private UserValidator validator = new UserValidator();
    private SignInValidator signInValidator = new SignInValidator();

    public String execute() throws Exception {
        return this.create();
    }

    //context user
    public String view() throws Exception {
        User user = Auth.getCurrentUser();

        if (user.getEmail() != null) {
            this.setCurrentUser(user);
        } else {
            this.setCurrentUser(null);
        }

        return SUCCESS;
    }

    //signup
    public String create() throws Exception {
        ValidateResult data = validator.validate(this);

        if (data.hasErrors()) {
            this.setValidateErrors(data.errors);
            return SUCCESS;
        }

        UserService.save(mapUser());
        Auth.setCurrentUser(this.session, this.getEmail());

        return SUCCESS;
    }

    //signin
    public String update() throws Exception {
        if (this.getSignout()) {
            Auth.unsetCurrentUser(this.session);
            return SUCCESS;
        }

        ValidateResult data = signInValidator.validate(this);

        if (data.hasErrors()) {
            this.setValidateErrors(data.errors);
            return SUCCESS;
        }

        Auth.setCurrentUser(this.session, this.getEmail());

        return SUCCESS;
    }

    private User mapUser() {
        User user = new User();
        user.setEmail(this.getEmail());
        user.setPassword(this.getPassword());

        return user;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return this.repeatPassword;
    }

    public void setRepeatPassword(String password) {
        this.repeatPassword = password;
    }

    public void setValidateErrors(List<Map<String, String>> errors) {
        this.validateErrors = errors;
    }

    public List<Map<String, String>> getValidateErrors() {
        return this.validateErrors;
    }

    public void setSession(Map<String, Object> map) {
        this.session = (SessionMap<String, Object>) map;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public boolean getSignout() { return this.signout; }

    public void setSignout(boolean signout) { this.signout = signout; }
}
