package com.example.volley.zeon.Model;

public class FutureVision {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = FutureVision.class.getSimpleName();
    /**
     * Short article resource about the Team orientations
     */
    private String mShortArticleFutureVision;

    /**
     * Meaningful Photo resource ID about the Team orientations
     */
    private int mImageArticleFutureVision;

    public FutureVision(String mShortArticleFutureVisiom, int mImageArticleFutureVision) {

        this.mShortArticleFutureVision = mShortArticleFutureVisiom;
        this.mImageArticleFutureVision = mImageArticleFutureVision;
    }

    public String getShortArticleFutureVisiom() {
        return mShortArticleFutureVision;
    }

    public void setShortArticleFutureVision(String mShortArticleFutureVisiom) {
        this.mShortArticleFutureVision = mShortArticleFutureVisiom;
    }

    public int getImageArticleFutureVision() {
        return mImageArticleFutureVision;
    }

    public void setImageArticleFutureVisiom(int mImageArticleFutureVision) {
        this.mImageArticleFutureVision = mImageArticleFutureVision;
    }
}
