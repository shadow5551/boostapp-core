package resources.users;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Result(type = "json")
public class UsersActions extends ActionSupport {

    private static final long serialVersionUID = 9037336567869476226L;
    private static final Logger log = LogManager.getLogger(UsersActions.class);

    public String execute() throws Exception {
        this.create();
        return SUCCESS;
    }

    public String create() throws Exception {
        User user = new User();
        user.setEmail("asd");
        UserService.save(user);
        return SUCCESS;
    }

}
