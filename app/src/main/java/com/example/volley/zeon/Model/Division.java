package com.example.volley.zeon.Model;

/**
 * {@link Division} represents a developers of members team  .
 * It contains a  string to represent the members in a card .
 */

public class Division {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = Division.class.getSimpleName();
    /**
     * name member resource for the member
     */
    private String mNameMember;

    /**
     * Majority's member resource for the member
     */
    private String mMajorityMember;

    /**
     * Image resource ID for the member
     */
    private int mImageMember;

    /**
     * Info summary resource for the member
     */
    private String mSummaryInfoMember;

    /**
     * Info complete resource for the member
     */
    private String mCompleteInfoMember;

    /**
     * Constant value that represents no state was provided for this member
     */
    private static final int NO_INFO_PROVIDED = -1;

    public Division(String mSummaryInfoMembers) {
        this.mSummaryInfoMember = mSummaryInfoMembers;
    }

    public Division(String mSummaryInfoMembers, int mImageMember) {
        this.mSummaryInfoMember = mSummaryInfoMembers;
        this.mImageMember = mImageMember;
    }

    public Division(String mNameMember, String majorityMember, int mImageMember, String mSummaryInfoMember) {
        this.mNameMember = mNameMember;
        this.mMajorityMember = majorityMember;
        this.mImageMember = mImageMember;
        this.mSummaryInfoMember = mSummaryInfoMember;

    }

    public String getNameMember() {
        return mNameMember;
    }

    public void setNameMember(String mNameMember) {
        this.mNameMember = mNameMember;
    }

    public String getMajorityMember() {
        return mMajorityMember;
    }

    public void setMajorityMember(String majorityMember) {
        this.mMajorityMember = majorityMember;
    }

    public int getImageMember() {
        return mImageMember;
    }

    public void setImageMember(int mImageMember) {
        this.mImageMember = mImageMember;
    }

    public String getSummaryInfoMember() {
        return mSummaryInfoMember;
    }

    public void setSummaryInfoMember(String mSummaryInfoMember) {
        this.mSummaryInfoMember = mSummaryInfoMember;
    }

    public String getCompleteInfoMember() {
        return mCompleteInfoMember;
    }

    public void setCompleteInfoMember(String mCompleteInfoMember) {
        this.mCompleteInfoMember = mCompleteInfoMember;
    }
}
