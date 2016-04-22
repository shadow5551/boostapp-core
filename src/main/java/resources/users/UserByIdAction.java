package resources.users;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resources.infrastructure.ValidateResult;
import resources.users.UserValidator;

import java.util.List;
import java.util.Map;

@Result(type = "json")
public class UserByIdAction extends ActionSupport {
    private Integer id;
    private User user;

    public String view() throws Exception {
        User user = UserService.getById(this.getId());
        this.setUser(user);

        return SUCCESS;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User u) {
        this.user = u;
    }
}
