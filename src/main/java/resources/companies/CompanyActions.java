package resources.companies;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Result;
import resources.infrastructure.Auth;
import resources.infrastructure.ValidateResult;
import resources.projects.Project;
import resources.projects.ProjectValidator;
import resources.users.User;

import java.util.List;
import java.util.Map;

@Result(type = "json")
public class CompanyActions extends ActionSupport {
    private int id;

    private List<Company> companies;
    private String title;
    private String tagLine;
    private Boolean remove;
    private int userId;

    private List<Map<String, String>> validateErrors;
    private CompanyValidator validator = new CompanyValidator();

    public String execute() throws Exception {
        return this.create();
    }

    public String view() throws Exception {
        List<Company> companies = CompanyService.getAll();
        this.setCompanies(companies);

        return SUCCESS;
    }

    public String update() throws Exception {
        if (this.getRemove()) {
            CompanyService.delete(this.getId());
            return SUCCESS;
        }

        ValidateResult data = validator.validate(this);

        if (data.hasErrors()) {
            this.setValidateErrors(data.errors);
            return SUCCESS;
        }

        CompanyService.update(mapCompany());

        return SUCCESS;
    }

    public String create() throws Exception {
        User user = Auth.getCurrentUser();

        ValidateResult data = validator.validate(this);

        if (data.hasErrors()) {
            this.setValidateErrors(data.errors);
            return SUCCESS;
        }

        Company company = CompanyService.save(mapCompany());

        CompanyMember cm = new CompanyMember();
        cm.setUserId(this.getUserId());
        cm.setCompanyId(company.getId());
        cm.setRole("creator");

        CompanyMembersService.save(cm);

        return SUCCESS;
    }

    private Company mapCompany() {
        Company company = new Company();

        company.setId(this.getId());
        company.setTitle(this.getTitle());
        company.setTagLine(this.getTagLine());

        return company;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getRemove() {
        return this.remove;
    }

    public void setRemove(Boolean remove) {
        this.remove = remove;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagLine() {
        return this.tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return this.companies;
    }

    public void setValidateErrors(List<Map<String, String>> errors) {
        this.validateErrors = errors;
    }

    public List<Map<String, String>> getValidateErrors() {
        return this.validateErrors;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }
}
