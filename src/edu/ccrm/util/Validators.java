package edu.ccrm.util;

public class Validators {

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("[\\w\\.-]+@[\\w\\.-]+\\.\\w+");
    }

    public static boolean isNonEmptyString(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
