package com.example.nysil.booklistapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Show_Books_List extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<BookData>>{
    private String URL_TO_GET_DATA;
    private ArrayAdapter<BookData> bookData;
    private BookAdapter bookAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__books__list);

        //getting user inputted query in url
        Intent intent=getIntent();
        URL_TO_GET_DATA=intent.getStringExtra("Url");


        ListView listView=(ListView)findViewById(R.id.list_item);

        bookAdapter=new BookAdapter(this, new ArrayList<BookData>());

        listView.setAdapter(bookAdapter);

        getLoaderManager().initLoader(1,null,this);
    }

    @Override
    public Loader<ArrayList<BookData>> onCreateLoader(int i, Bundle bundle) {

        BookLoader bookLoader=new BookLoader(this, URL_TO_GET_DATA);
        return bookLoader;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<BookData>> loader, ArrayList<BookData> bookData) {
        bookAdapter.addAll(bookData);

    }

    @Override
    public void onLoaderReset(Loader loader) {
        loader.reset();

    }
}
