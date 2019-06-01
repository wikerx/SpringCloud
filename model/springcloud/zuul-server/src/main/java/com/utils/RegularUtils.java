package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularUtils {

    public static boolean isMatcherString(String inputStr, String regex, boolean isIgnore) {
        try {
            Pattern pattern = null;
            if (isIgnore) {
                pattern = Pattern.compile(regex, 2);
            } else {
                pattern = Pattern.compile(regex);
            }
            Matcher matcher = pattern.matcher(inputStr);
            return matcher.find();
        } catch (Exception e) {
        }
        return false;
    }

    public static String[] splitStringToArray(String inputStr, String regex) {
        try {
            Pattern pattern = Pattern.compile(regex);
            return pattern.split(inputStr);
        } catch (Exception e) {
        }
        return null;
    }

    public static String[] splitStringToArray(String inputStr, String regex, int limit) {
        try {
            Pattern pattern = Pattern.compile(regex);
            return pattern.split(inputStr, limit);
        } catch (Exception e) {
        }
        return null;
    }

    public static String replaceString(String inputStr, String orgStr, String newStr) {
        try {
            Pattern pattern = Pattern.compile(orgStr);
            Matcher matcher = pattern.matcher(inputStr);
            return matcher.replaceAll(newStr);
        } catch (Exception e) {
        }
        return null;
    }

    public static boolean matchingStringByRegular(String inputParam, String regex) {
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(inputParam);
            return matcher.matches();
        } catch (Exception e) {
        }
        return false;
    }
}
