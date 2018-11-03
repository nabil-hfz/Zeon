package com.example.volley.zeon.Model;

public class AboutUS {


    /**
     * summary about Conditions for foundation the team resource .
     */
    private String mShortSummaryforFoundationTeam;

    /**
     * Group photo of the team resource ID .
     */
    private int mImagephotoOfTheTeam;

    /**
     * Address " Contact us " for the team resource .
     */
    private String mAddressContactUsTeam;

    public AboutUS(String mShortSummaryforFoundationTeam, int mImagephotoOfTheTeam, String mAddressContactUsTeam) {
        this.mShortSummaryforFoundationTeam = mShortSummaryforFoundationTeam;
        this.mImagephotoOfTheTeam = mImagephotoOfTheTeam;
        this.mAddressContactUsTeam = mAddressContactUsTeam;
    }

    public String getShortSummaryforFoundationTeam() {
        return mShortSummaryforFoundationTeam;
    }

    public void setShortSummaryforFoundationTeam(String mShortSummaryforFoundationTeam) {
        this.mShortSummaryforFoundationTeam = mShortSummaryforFoundationTeam;
    }

    public int getImagephotoOfTheTeam() {
        return mImagephotoOfTheTeam;
    }

    public void setImagephotoOfTheTeam(int mImagephotoOfTheTeam) {
        this.mImagephotoOfTheTeam = mImagephotoOfTheTeam;
    }

    public String getAddressContactUsTeam() {
        return mAddressContactUsTeam;
    }

    public void setAddressContactUsTeam(String mAddressContactUsTeam) {
        this.mAddressContactUsTeam = mAddressContactUsTeam;
    }
}
