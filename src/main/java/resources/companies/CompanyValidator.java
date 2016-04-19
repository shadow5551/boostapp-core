package resources.companies;

import resources.infrastructure.Auth;
import resources.infrastructure.ValidateResult;
import resources.infrastructure.Validator;
import resources.users.User;

public class CompanyValidator extends Validator {

    public ValidateResult validate(CompanyActions params) {

        User user = Auth.getCurrentUser();

        if (user == null) {
            this.addError("user", "You are not loggged in");
        }

        String title = params.getTitle();
        String tagLine = params.getTagLine();

        if (title == null || tagLine == null ) {
            this.addError("all", "All fields are required");
        }

        ValidateResult result = new ValidateResult();
        if (this.hasErrors()) {
            result.errors = this.getErrors();
        }

        return result;
    }
}

