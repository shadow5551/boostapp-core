package resources.news;

import resources.companies.Company;
import resources.companies.CompanyMember;
import resources.companies.CompanyMembersService;
import resources.companies.CompanyService;
import resources.infrastructure.Auth;
import resources.infrastructure.ValidateResult;
import resources.infrastructure.Validator;
import resources.users.User;

/**
 * Created by root on 13.9.16.
 */
public class NewsValidator extends Validator {
    public ValidateResult validate(NewsAction params) {
        ValidateResult result = new ValidateResult();

        User user = Auth.getCurrentUser();
        if (user == null) {
            this.addError("user", "You are not logged in");
            result.errors = this.getErrors();
            return result;
        }

        String title = params.getTitle();
        String content = params.getContent();
        Integer companyId = params.getCompanyId();

        if (title == null || title.length() == 0 || content == null || content.length() == 0) {
            this.addError("all", "All fields are required");
            result.errors = this.getErrors();
            return result;
        }

        if (title.length() > 100) {
            this.addError("title", "title could not be more than 100 symbols");
        }

        if (content.length() > 500 ) {
            this.addError("desc", "desc could not be more than 500 symbols");
        }

        if (companyId == null) {
            this.addError("company", "Please select company");
        }

        if (this.hasErrors()) {
            result.errors = this.getErrors();
            return result;
        }

        Company company = CompanyService.getById(companyId);
        if (company == null) {
            this.addError("company", "This company does not exists");
            result.errors = this.getErrors();
            return result;
        }

        CompanyMember cm = CompanyMembersService.getUserInCompany(user.getId(), companyId);
        if (cm == null) {
            this.addError("company", "You are not allowed to create or update news for this company");
        } else if(!cm.getRole().contentEquals("creator") && !cm.getRole().contentEquals("manager")) {
            this.addError("permission", "You have not enough permissions to create or update news for this company");
        }

        if (this.hasErrors()) {
            result.errors = this.getErrors();
            return result;
        }

        return result;
    }
}
