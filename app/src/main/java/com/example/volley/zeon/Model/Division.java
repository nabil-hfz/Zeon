package com.example.volley.zeon.Model;


/**
 * Author of this class is Nabil  in 2018/03/11
 */

/**
 * {@link Division} represents a developers of members team  .
 * It contains a  string to represent the members in a card .
 * <p>
 * It contains  Name Member Team in String var .
 * <p>
 * It contains Majority Member Of The Team  in String var .
 * <p>
 * It contains Image for team Member in int var .
 * <p>
 * It contains Summary Info about team Member in string var .
 * <p>
 * It contains Complete Info about team Member in string var .
 **/

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
