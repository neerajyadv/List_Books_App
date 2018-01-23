package com.example.nysil.booklistapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


public class BookAdapter extends ArrayAdapter<BookData> {

    public BookAdapter(@NonNull Context context, ArrayList<BookData> bookdata) {
        super(context,0, bookdata);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listView=convertView;

        if(listView==null)
        {
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.booklistdata_layout, parent, false);
        }

        BookData bookData=getItem(position);

        //now we start putting data in our views

        TextView tv1=(TextView)listView.findViewById(R.id.book_Name);
        String bookName= bookData.getBookName();
        tv1.setText(bookName);

        TextView tv2=(TextView)listView.findViewById(R.id.author_Name);
        String authorName= bookData.getAuthorName();
        tv2.setText(authorName);

        return listView;
    }
}
