package validator;

import entity.User;

public class UserValidator {

    public static void validateAddNewUserFlow(User user){

        if (user == null || user.getUsername() == null) {
            throw new IllegalArgumentException("User is null or name empty.");
        }


    }
}
