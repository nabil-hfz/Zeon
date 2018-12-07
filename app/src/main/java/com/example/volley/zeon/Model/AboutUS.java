package com.example.volley.zeon.Model;

/**
 * Author of this class is Nabil  in 2018/03/11
 */

/**
 * {@link AboutUS} represents a Info about contact with members team  .
 * <p>
 * It contains  Short Summary For Foundation Team in String var .
 * <p>
 * It contains Image Photo Of The Team  in int var .
 * <p>
 * It contains Address to Contact to Zeon team  in String var .
 */

public class AboutUS {
    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = AboutUS.class.getSimpleName();

    /**
     * summary about Conditions for foundation the team resource .
     */
    private String mShortSummaryForFoundationTeam;

    /**
     * Group photo of the team resource ID .
     */
    private String mImagePhotoOfTheTeam;

    /**
     * Address " Contact us " for the team resource .
     */
    private String mAddressContactUsTeam;

    public AboutUS(String mShortSummaryForFoundationTeam, String mImagePhotoOfTheTeam, String mAddressContactUsTeam) {
        this.mShortSummaryForFoundationTeam = mShortSummaryForFoundationTeam;
        this.mImagePhotoOfTheTeam = mImagePhotoOfTheTeam;
        this.mAddressContactUsTeam = mAddressContactUsTeam;
    }

    public String getShortSummaryforFoundationTeam() {
        return mShortSummaryForFoundationTeam;
    }

    public void setShortSummaryforFoundationTeam(String mShortSummaryforFoundationTeam) {
        this.mShortSummaryForFoundationTeam = mShortSummaryforFoundationTeam;
    }

    public String getImagePhotoOfTheTeam() {
        return mImagePhotoOfTheTeam;
    }

    public void setImagePhotoOfTheTeam(String mImagephotoOfTheTeam) {
        this.mImagePhotoOfTheTeam = mImagephotoOfTheTeam;
    }

    public String getAddressContactUsTeam() {
        return mAddressContactUsTeam;
    }

    public void setAddressContactUsTeam(String mAddressContactUsTeam) {
        this.mAddressContactUsTeam = mAddressContactUsTeam;
    }
}
