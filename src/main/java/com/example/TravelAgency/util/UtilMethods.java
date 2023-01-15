package com.example.TravelAgency.util;

import com.example.TravelAgency.exception.InvalidFormat;
import org.apache.commons.text.RandomStringGenerator;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilMethods {

    private static final int LENGTH = 16;

    public static String generatePassword() {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator
                .Builder()
                .withinRange(33, 45)
                .build();
        return pwdGenerator.generate(LENGTH);
    }


    public static boolean verifyEmail(String emailAddress) {
        String emailPattern = "^[\\w.+\\-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }


}

