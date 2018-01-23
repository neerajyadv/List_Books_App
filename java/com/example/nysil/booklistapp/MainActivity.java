package com.example.nysil.booklistapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String name=null;
    private String rating=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final EditText editText1=(EditText)findViewById(R.id.topic);
        Button button=(Button)findViewById(R.id.search_button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name= String.valueOf(editText1.getText());
                String topic=name.toLowerCase();

                //now use the user input to create query url
                String URL_TO_GET_LIST="https://www.googleapis.com/books/v1/volumes?q="+topic+"&maxResults=15";


                //use this url to start a background thread which can get data for user


                Intent intent=new Intent(MainActivity.this, Show_Books_List.class);
                intent.putExtra("Url", URL_TO_GET_LIST);
                startActivity(intent);

            }
        });

    }
}
