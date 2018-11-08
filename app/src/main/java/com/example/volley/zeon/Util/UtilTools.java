package com.example.volley.zeon.Util;

import android.text.TextUtils;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilTools {

    public final static boolean isValidEmail(CharSequence target) {

        Pattern patternEmail = Pattern.compile(".+@.+\\.[a-z]+");

        // String email = "xyz@xyzdomain.com";

        Matcher matcherEmail = patternEmail.matcher(target);

        boolean matchFound = matcherEmail.matches();
        Log.v("UtilTools", "\nnabel UtilTools\n");

        if (TextUtils.isEmpty(target) || !matchFound) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches() && matchFound;
        }
    }
}
