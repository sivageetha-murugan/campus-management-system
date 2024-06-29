package com.i2i.cms.utils;

public final class ValidateInputUtils {

    private ValidateInputUtils() {}

    /**
     *<p>
     * This method checks whether the given grade is within the range from 1 to 12.
     *</p>
     * 
     * @param grade 
     *        The grade which is to be validated.
     *
     * @return True if the grade is within the range, Ex: 12 
     *         False if the grade exceeds or lesser than, 0 Ex: 0 0r 13.
     */

    public static boolean isValidGrade(int grade) {
        return (grade < 13 && grade >0);
    }

    /**
     *<p>
     * This method checks whether the given string is valid and contain alphabets only and does not contain any other special characters.
     *</p>
     * 
     * @param string 
     *        The string which is to be validated.
     *
     * @return True if the string contains alphabets only.
     *         False if the string contains numbers or any special characters.
     */

    public static boolean isValidString(String string) {
        String regex = "^[a-zA-Z\\s]+$";
        return string.matches(regex) && !string.isEmpty();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 10) {
            return false;
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                return false;
            }
        }   
        return true;
    }

}

        