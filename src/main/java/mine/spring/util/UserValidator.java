package mine.spring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import mine.spring.models.User;
import mine.spring.service.UserService;

@Component
public class UserValidator implements Validator {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (userService.showEmail(user.getEmail()).isPresent()) {
            errors.rejectValue("email", null, "Email is already in use");
        }
    }
}
