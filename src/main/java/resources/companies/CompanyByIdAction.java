package resources.companies;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Result;
import resources.infrastructure.Auth;
import resources.infrastructure.ValidateResult;
import resources.projects.Project;
import resources.projects.ProjectValidator;
import resources.users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Result(type = "json")
public class CompanyByIdAction extends ActionSupport {
    private int id;

    private Company company;

    public String view() throws Exception {
        this.setCompany(CompanyService.getById(this.getId()));

        return SUCCESS;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
