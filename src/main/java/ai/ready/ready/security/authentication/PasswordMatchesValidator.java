package ai.ready.ready.security.authentication;

import ai.ready.ready.security.authentication.dto.RegistrationRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches passwordMatches) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        RegistrationRequest registrationRequest = (RegistrationRequest) o;
        return registrationRequest.getPassword().equals(registrationRequest.getMatchingPassword());
    }
}
