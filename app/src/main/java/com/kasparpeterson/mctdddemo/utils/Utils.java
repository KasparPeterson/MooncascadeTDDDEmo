package com.kasparpeterson.mctdddemo.utils;

import android.support.annotation.Nullable;

/**
 * Created by kaspar on 01/04/2017.
 */

public class Utils {

    public static boolean isStringEmpty(@Nullable CharSequence charSequence) {
        return charSequence == null || charSequence.toString().trim().length() == 0;
    }

    public static boolean hasCapitalLetter(@Nullable CharSequence charSequence) {
        if (charSequence != null) {
            for (int i = 0; i < charSequence.length(); i++) {
                if (Character.isUpperCase(charSequence.charAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasNumberInIt(@Nullable CharSequence charSequence) {
        if (charSequence != null) {
            for (int i = 0; i < charSequence.length(); i++) {
                if (Character.isDigit(charSequence.charAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }

}
