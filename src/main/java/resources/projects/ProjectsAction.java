package resources.projects;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Result(type = "json")
public class ProjectsAction extends ActionSupport {

    private static final long serialVersionUID = 9037336532369476225L;
    private static final Logger log = LogManager.getLogger(ProjectsAction.class);

    private List<Project> projects;

    public String execute() throws Exception {
        return this.view();
    }

    public String view() throws Exception {
        List<Project> projects = ProjectService.getAll();
        this.setProjects(projects);

        return SUCCESS;
    }

    public List<Project> getProjectNames() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
