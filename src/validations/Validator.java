package validations;

import java.util.regex.Pattern;

public class Validator {

    public static boolean validateEmail(String email) {
        Pattern mail = Pattern.compile("^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}");
        if (mail.matcher(email).matches()) {
            return true;
        } else {
            System.out.println("Enter valid email address");
            return false;
        }
    }

    public static boolean validatePhone(String phoneNumber) {
        if (phoneNumber.length() != 10) {
            System.out.println("length should be 10 digit");
            return false;
        }
        if (phoneNumber.matches("([1-9])([\\d]+){9}")) {
            return true;
        }
        else {
            System.out.println("Enter valid phone number.");
            return false;
        }

    }


    public static boolean validateName(String name) {
//        input must not be of any single primitive type only
//        (character type is allowed), special character is not allowed.

        if (name.matches("\\d+")) {
            System.out.println("You have entered 'Integer' type value, Please enter a 'String'");
            return false;
        } else if (name.matches("\\d*[.]\\d+")) {
            System.out.println("You have entered 'Double' type value, Please enter a 'String'");
            return false;
        } else if (name.matches("true|false")) {
            System.out.println("You have entered 'Boolean' type value, Please enter a 'String'");
            return false;
        } else if (!name.matches("^((?=[A-Za-z0-9@])(?![_\\\\-]).)*$")) {
            System.out.println("Restricted special character found, please enter valid name");
            return false;
        } else if (name.length() > 50) {
            System.out.println("Name is too long");
            return false;
        } else {
            return true;
        }
    }

    public static Boolean validateAccountType(int accountType) {
        if (accountType == 1 || accountType == 2 || accountType == 3) {
            return true;
        } else {
            System.out.println("Please enter valid number...!");
            return false;
        }
    }

    public static boolean validateInitialBalance(double balance) {
        if (balance < 100) {
            System.out.println("Minimum balance required is 100 rs.");
            return false;
        }
        return true;
    }
}
