package resources.users;

import resources.infrastructure.ValidateResult;
import resources.infrastructure.Validator;
import resources.projects.ProjectsAction;

public class SignInValidator extends Validator {

    public ValidateResult validate(UsersActions params) {
        ValidateResult result = new ValidateResult();
        String email = params.getEmail();
        String password = params.getPassword();

        if (email == null || password == null) {
            this.addError("all", "All fields are required");
        }

        if (this.hasErrors()) {
            result.errors = this.getErrors();
            return result;
        }

        User user = UserService.getByEmail(email);
        if (user == null) {
            this.addError("user", "Such user does not exists");
        } else if (!user.getPassword().contentEquals(password)) {
            this.addError("user", "Invalid credentials");
        }

        if (this.hasErrors()) {
            result.errors = this.getErrors();
        }

        return result;
    }

}
