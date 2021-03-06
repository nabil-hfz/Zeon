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
     *  member id
     */
    private int id;

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
    private String mImageMember;



    /**
     * Constant value that represents no state was provided for this member
     */
    private static final int NO_INFO_PROVIDED = -1;


    public Division() {

    }

    public Division(int id,String mNameMember, String majorityMember, String mImageMember) {
        this.id=id;
        this.mNameMember = mNameMember;
        this.mMajorityMember = majorityMember;
        this.mImageMember = mImageMember;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImageMemberUrl() {
        return mImageMember;
    }

    public void setImageMemberUrl(String mImageMember) {
        this.mImageMember = mImageMember;
    }





}
