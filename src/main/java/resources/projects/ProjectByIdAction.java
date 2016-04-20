package resources.projects;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resources.infrastructure.ValidateResult;
import resources.users.UserValidator;

import java.util.List;
import java.util.Map;

@Result(type = "json")
public class ProjectByIdAction extends ActionSupport {
    private static final long serialVersionUID = 9037336532369476225L;
    private static final Logger log = LogManager.getLogger(ProjectsAction.class);

    private Integer id;
    private Project project;

    public String view() throws Exception {
        Project project = ProjectService.getById(this.getId());
        this.setProject(project);

        return SUCCESS;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project p) {
        this.project = p;
    }
}
