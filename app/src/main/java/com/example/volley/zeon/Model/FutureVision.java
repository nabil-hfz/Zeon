package com.example.volley.zeon.Model;

/**
 * Author of this class is Nabil  in 2018/03/11
 **/

/**
 * {@link FutureVision} represents a Future Vision of members team  .
 * <p>
 * It contains  Short Article Future Vision for  Team in String var .
 * <p>
 * It contains Image Photo Of Future Vision in int var .
 */
public class FutureVision {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = FutureVision.class.getSimpleName();
    /**
     * Short article resource about the Team orientations
     */
    private String mTitle;

    private String mShortArticleFutureVision;

    public FutureVision() {
    }

    public FutureVision(String mTitle, String mShortArticleFutureVision) {
        this.mTitle = mTitle;
        this.mShortArticleFutureVision = mShortArticleFutureVision;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getShortArticleFutureVision() {
        return mShortArticleFutureVision;
    }

    public void setShortArticleFutureVision(String mShortArticleFutureVision) {
        this.mShortArticleFutureVision = mShortArticleFutureVision;
    }
}
