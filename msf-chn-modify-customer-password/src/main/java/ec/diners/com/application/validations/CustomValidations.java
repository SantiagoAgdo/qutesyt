package ec.diners.com.application.validations;

import java.util.regex.Pattern;

public class CustomValidations {

    private CustomValidations() {
    }

    public static boolean validEmail(String email) {
        var emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return Pattern.compile(emailPattern).matcher(email).matches();
    }

    public static boolean validPassword(String password) {
        var passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        return Pattern.compile(passwordPattern).matcher(password).matches();
    }
}
