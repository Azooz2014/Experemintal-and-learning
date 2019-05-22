package com.example.android.miwok;

import android.media.MediaPlayer;

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 */

public class Word {

    private String mMiwokWord;
    private String mEnglishWord;
    private int mImgResourceId;
    private boolean mhasImage;
    private int mAudioResourseId;

    //Public constructors.

    public Word(){
    }

    /**
    * @param englishWord The desired word in english.
    * @param miwokWord The translation of the english word in miwok.
    * @param hasImage whether the activity has an image associated with it or not.
    * */


    public Word (String englishWord, String miwokWord, boolean hasImage){

        this.mMiwokWord = miwokWord;
        this.mEnglishWord = englishWord;
        this.mhasImage = hasImage;
    }

    /**
     * @param englishWord The desired word in english.
     * @param miwokWord The translation of the english word in miwok.
     * @param imgResourceId The resource Id of the desired image.
     * @param hasImage whether the activity has an image associated with it or not.
     *
     * */

    public Word (String englishWord, String miwokWord, int imgResourceId, boolean hasImage){

        this.mMiwokWord = miwokWord;
        this.mEnglishWord = englishWord;
        this.mImgResourceId = imgResourceId;
        this.mhasImage = hasImage;
    }

    /**
     * @param englishWord The desired word in english.
     * @param miwokWord The translation of the english word in miwok.
     * @param hasImage Whether the activity has an image associated with it or not.
     * @param audioResourceId The desired AudioResourceId to be played by MediaPlayer.
     *
     * */

    public Word (String englishWord, String miwokWord, boolean hasImage, int audioResourceId){

        this.mMiwokWord = miwokWord;
        this.mEnglishWord = englishWord;
        this.mhasImage = hasImage;
        this.mAudioResourseId = audioResourceId;
    }

    /**
     * @param englishWord The desired word in english.
     * @param miwokWord The translation of the english word in miwok.
     * @param imgResourceId The resource Id of the desired image.
     * @param hasImage whether the activity has an image associated with it or not.
     * @param audioResourceId The desired AudioResourceId to be played by MediaPlayer.
     * */

    public Word (String englishWord, String miwokWord, int imgResourceId, boolean hasImage, int audioResourceId){

        this.mMiwokWord = miwokWord;
        this.mEnglishWord = englishWord;
        this.mImgResourceId = imgResourceId;
        this.mhasImage = hasImage;
        this.mAudioResourseId = audioResourceId;
    }

   /* public Word (String englishWord, String miwokWord, int imgResourceId, boolean hasImage, int TagId){

        this.mMiwokWord = miwokWord;
        this.mEnglishWord = englishWord;
        this.mImgResourceId = imgResourceId;
        this.mhasImage = hasImage;
        this.mTagId = TagId;
    }*/

    //Get Miwok translation of the word.
    public String getMiwokTranslation(){

        return mMiwokWord;
    }

    //Get Default translation of the word.
    public String getDefaultTranslation(){

        return mEnglishWord;
    }

    public int getmImgResourceId(){

        return mImgResourceId;
    }

    //Returns the valuse of mHasImage value.
    public boolean hasImage(){

        return mhasImage;
    }

    public int getAudioResourseId(){

        return mAudioResourseId;
    }

    //toString method used for debugging purposes, to print the content of the public getters.
    @Override
    public String toString() {
        return "Word{" +
                "mMiwokWord='" + mMiwokWord + '\'' +
                ", mEnglishWord='" + mEnglishWord + '\'' +
                ", mImgResourceId=" + mImgResourceId +
                ", mhasImage=" + mhasImage +
                ", mAudioResourseId=" + mAudioResourseId +
                '}';
    }
}
