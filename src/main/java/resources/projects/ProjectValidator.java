package resources.projects;

import resources.infrastructure.ValidateResult;
import resources.infrastructure.Validator;

public class ProjectValidator extends Validator {

    public ValidateResult validate(ProjectsAction params) {
        String title = params.getTitle();
        String description = params.getDescription();
        Integer amount = params.getAmount();
        Integer companyId = params.getCompanyId();

        if (title == null || description == null || amount == null) {
            this.addError("all", "All fields are required");
        }

        if (companyId == null) {
            this.addError("company", "Please select company");
        }

        ValidateResult result = new ValidateResult();
        if (this.hasErrors()) {
            result.errors = this.getErrors();
        }

        return result;
    }
}

