package resources.companies;

import resources.infrastructure.ValidateResult;
import resources.infrastructure.Validator;

public class CompanyValidator extends Validator {

    public ValidateResult validate(CompanyActions params) {
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

