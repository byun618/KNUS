package com.knu.knus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {
    public static final Pattern VALID_STDNUM_REGEX = Pattern.compile("[0-9]{10}");
    public static final Pattern VALID_PASWORD_REGEX = Pattern.compile("^[a-z0-9!@.#$%^&*?_~]{6,25}$");

    public static boolean validateStdnum(String stdnum){
        Matcher matcher = VALID_STDNUM_REGEX.matcher(stdnum);
        return matcher.matches();
    }

    public static boolean validatePassword(String pwd){
        Matcher matcher = VALID_PASWORD_REGEX.matcher(pwd);
        return matcher.matches();
    }

}
