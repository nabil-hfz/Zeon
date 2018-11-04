package com.example.volley.zeon.Model;

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
    private int mImagePhotoOfTheTeam;

    /**
     * Address " Contact us " for the team resource .
     */
    private String mAddressContactUsTeam;

    public AboutUS(String mShortSummaryforFoundationTeam, int mImagephotoOfTheTeam, String mAddressContactUsTeam) {
        this.mShortSummaryForFoundationTeam = mShortSummaryforFoundationTeam;
        this.mImagePhotoOfTheTeam = mImagephotoOfTheTeam;
        this.mAddressContactUsTeam = mAddressContactUsTeam;
    }

    public String getShortSummaryforFoundationTeam() {
        return mShortSummaryForFoundationTeam;
    }

    public void setShortSummaryforFoundationTeam(String mShortSummaryforFoundationTeam) {
        this.mShortSummaryForFoundationTeam = mShortSummaryforFoundationTeam;
    }

    public int getImagephotoOfTheTeam() {
        return mImagePhotoOfTheTeam;
    }

    public void setImagephotoOfTheTeam(int mImagephotoOfTheTeam) {
        this.mImagePhotoOfTheTeam = mImagephotoOfTheTeam;
    }

    public String getAddressContactUsTeam() {
        return mAddressContactUsTeam;
    }

    public void setAddressContactUsTeam(String mAddressContactUsTeam) {
        this.mAddressContactUsTeam = mAddressContactUsTeam;
    }
}
