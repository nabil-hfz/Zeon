package com.example.volley.zeon.Model;

public class FutureVision {

    /**
     * Short article resource about the Team orientations
     */
    private String mShortArticleFutureVisiom;

    /**
     * Meaningful Photo resource ID about the Team orientations
     */
    private int mImageArticleFutureVisiom;

    public FutureVision(String mShortArticleFutureVisiom, int mImageArticleFutureVisiom) {
        this.mShortArticleFutureVisiom = mShortArticleFutureVisiom;
        this.mImageArticleFutureVisiom = mImageArticleFutureVisiom;
    }

    public String getShortArticleFutureVisiom() {
        return mShortArticleFutureVisiom;
    }

    public void setShortArticleFutureVisiom(String mShortArticleFutureVisiom) {
        this.mShortArticleFutureVisiom = mShortArticleFutureVisiom;
    }

    public int getImageArticleFutureVisiom() {
        return mImageArticleFutureVisiom;
    }

    public void setImageArticleFutureVisiom(int mImageArticleFutureVisiom) {
        this.mImageArticleFutureVisiom = mImageArticleFutureVisiom;
    }
}
