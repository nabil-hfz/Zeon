package com.example.volley.zeon.Model;

public class FutureVision {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = FutureVision.class.getSimpleName();
    /**
     * Short article resource about the Team orientations
     */
    private String mShortArticleFutureVisiom;

    /**
     * Meaningful Photo resource ID about the Team orientations
     */
    private int mImageArticleFutureVision;

    public FutureVision(String mShortArticleFutureVisiom, int mImageArticleFutureVision) {

        this.mShortArticleFutureVisiom = mShortArticleFutureVisiom;
        this.mImageArticleFutureVision = mImageArticleFutureVision;
    }

    public String getShortArticleFutureVisiom() {
        return mShortArticleFutureVisiom;
    }

    public void setShortArticleFutureVisiom(String mShortArticleFutureVisiom) {
        this.mShortArticleFutureVisiom = mShortArticleFutureVisiom;
    }

    public int getImageArticleFutureVisiom() {
        return mImageArticleFutureVision;
    }

    public void setImageArticleFutureVisiom(int mImageArticleFutureVision) {
        this.mImageArticleFutureVision = mImageArticleFutureVision;
    }
}
