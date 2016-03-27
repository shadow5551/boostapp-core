package resources.users;

import resources.infrastructure.ValidateResult;
import resources.infrastructure.Validator;

public class UserValidator extends Validator {

    public ValidateResult validate(UsersActions params) {
            String email = params.getEmail();
            String password = params.getPassword();
            String repeatPassword = params.getRepeatPassword();

            if (password == null || email == null || repeatPassword == null) {
                this.addError("all", "All fields are required");
            } else if (!password.contentEquals(repeatPassword)) {
                this.addError("password", "Password should be equals");
            }

            ValidateResult result = new ValidateResult();
            if (this.hasErrors()) {
                result.errors = this.getErrors();
            }

            return result;
    }
}

