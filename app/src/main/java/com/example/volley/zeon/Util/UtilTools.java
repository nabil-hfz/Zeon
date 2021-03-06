package com.example.volley.zeon.Util;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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

    public final static Intent makeIntentOfEmail(String email) {
        Intent emailIntent = new Intent(Intent.ACTION_VIEW);
        emailIntent.setData(Uri.parse(email));
        return emailIntent;
    }

    public final static Intent makeIntentToOpenPhotoInGalleryBrowser(String ImageUrl) {
        Uri ImageMemberUrl = Uri.parse(ImageUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(ImageMemberUrl, "image/*");
        return intent;
    }

    /**
     * This for Enabling https connections
     */
    public static class handleSSLHandshake {
        protected static final String TAG = "NukeSSLCerts";

        public static void nuke() {
            try {
                TrustManager[] trustAllCerts = new TrustManager[]{
                        new X509TrustManager() {
                            public X509Certificate[] getAcceptedIssuers() {
                                X509Certificate[] myTrustedAnchors = new X509Certificate[0];
                                return myTrustedAnchors;
                            }

                            @Override
                            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                            }

                            @Override
                            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                            }
                        }
                };

                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String arg0, SSLSession arg1) {
                        return true;
                    }
                });
            } catch (Exception e) {
            }
        }
    }
}
