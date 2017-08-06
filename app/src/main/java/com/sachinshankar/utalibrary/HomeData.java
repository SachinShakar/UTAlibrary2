package com.sachinshankar.utalibrary;


public class HomeData {
    private String mCategory;
    private int mCentral_lib;
    private int mArchitecture_lib;
    private int mScience_lib;

    public HomeData(String cat,int cen,int afa, int sel)
    {
        mArchitecture_lib = afa;
        mCategory = cat;
        mCentral_lib = cen;
        mScience_lib = sel;
    }
    public String getmCategory(){
        return mCategory;
    }

    public int getmCentral_lib(){
        return mCentral_lib;
    }
    public int getmArchitecture_lib(){
        return mArchitecture_lib;
    }
    public int getmScience_lib(){
        return mScience_lib;
    }
}
