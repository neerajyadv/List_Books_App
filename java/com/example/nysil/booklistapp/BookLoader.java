package com.example.nysil.booklistapp;


import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

class BookLoader extends AsyncTaskLoader<ArrayList<BookData>>{

    String Get_Data_From;
    public BookLoader(Context context, String Get_Data_From) {
        super(context);
        this.Get_Data_From=Get_Data_From;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<BookData> loadInBackground() {

        ArrayList<BookData> bookData= QueryData.getBookData(Get_Data_From);
        return bookData;
    }
}
