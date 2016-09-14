package resources.news;

import resources.companies.CompanyMember;
import resources.companies.CompanyMembersService;
import resources.infrastructure.Auth;
import resources.infrastructure.ValidateResult;
import resources.infrastructure.Validator;
import resources.users.User;

/**
 * Created by root on 13.9.16.
 */

public class NewsRemoveValidator extends Validator {
    public ValidateResult validate(NewsAction params) {
        ValidateResult result = new ValidateResult();

        User user = Auth.getCurrentUser();

        if (user == null) {
            this.addError("user", "You are not logged in");
            result.errors = this.getErrors();
            return result;
        }

        Integer newsId = params.getId();

        if (newsId == null) {
            this.addError("company", "Please select news");
        }

        News news = NewsService.getById(newsId);
        if (news == null) {
            this.addError("news", "Such news does not exists");
            result.errors = this.getErrors();
            return result;
        }

        CompanyMember cm = CompanyMembersService.getUserInCompany(user.getId(), news.getCompanyId());
        if (cm == null) {
            this.addError("permission", "You are not allowed to remove this news");
        } else if (!cm.getRole().contentEquals("creator")) {
            this.addError("permission", "Only company owner or manager can remove news");
        }

        if (this.hasErrors()) {
            result.errors = this.getErrors();
        }

        return result;
    }
}
