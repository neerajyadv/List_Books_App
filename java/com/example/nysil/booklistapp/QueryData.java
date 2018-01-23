package com.example.nysil.booklistapp;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public final class QueryData {
    public static final String LOG_TAG = QueryData.class.getSimpleName();

    private QueryData(){
    //private constructor because we want no one to access it or initialize it
     }

     //in this class we extract the data fully from url and give back to the activity

    public static ArrayList<BookData> getBookData(String get_Data_From) {

        //create URL from the giving string
        URL url=getURL(get_Data_From);

        //now when we have formed url and got it in url,
        //use it to create connection and then get JSon response back

        String dataFromJson=null;
        try {
             dataFromJson = readResponse(url);
        }catch (IOException e){System.out.println(e);}

        //when we got full data, extract relevant fields from it
        ArrayList<BookData> bookData= getRelevantFields(dataFromJson);

        return bookData;
    }

    private static ArrayList<BookData> getRelevantFields(String dataFromJson) {

        String bookName = null;
        String authorName = null;
        if (dataFromJson == null) {
            return null;
        } else {
            ArrayList<BookData> arrayList = new ArrayList<>();
            try {
                JSONObject rootJsonObject = new JSONObject(dataFromJson);

                JSONArray rootArray = rootJsonObject.getJSONArray("items");

                if (rootArray.length() > 0)
                {
                for (int i = 0; i < rootArray.length(); i++) {
                    StringBuilder authorNames = new StringBuilder();
                    JSONObject jsonObjectInArray = rootArray.getJSONObject(i);

                    JSONObject jsonMainObject = jsonObjectInArray.getJSONObject("volumeInfo");


                    JSONArray jsonArrayInsideMainObject = jsonMainObject.getJSONArray("authors");

                    for (int j = 0; j < jsonArrayInsideMainObject.length(); j++) {
                        String authorNameObject = jsonArrayInsideMainObject.getString(j);
                        authorNames.append(authorNameObject +" ");
                    }

                    bookName = jsonMainObject.optString("title");

                    authorName = authorNames.toString();

                    BookData bookData = new BookData(bookName, authorName);
                    arrayList.add(bookData);
                    authorNames =null;
                }
             }

            } catch (JSONException e) {
                System.out.println("error while parsing json " + e);
            }
            return arrayList;
        }
    }

    private static String readResponse(URL url) throws IOException {

        String jsonResponse=null;

        if(url==null)
        {
            return null;
        }
        else
        {
            HttpURLConnection httpURLConnection=null;
            InputStream inputStream=null;
            try {
                httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setConnectTimeout(20000);
                httpURLConnection.connect();

                if(httpURLConnection.getResponseCode()== httpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                }
           else {
                    Log.e(LOG_TAG, "Error response code: " + httpURLConnection.getResponseCode());
                }
                } catch (IOException e) {
                    System.out.println("Cannot get data from url error in inout stream " + e);
                } finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
                return jsonResponse;
            }

        }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder reading=new StringBuilder();
        if(inputStream==null)
        {
            return null;
        }
        else
        {
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String stringB = bufferedReader.readLine();
                while (stringB != null) {
                    reading.append(stringB);
                    stringB = bufferedReader.readLine();
                }
            } catch (IOException e){System.out.println("Error while getting data from stream "+e);}
        }
        return reading.toString();
       // String read= reading.toString();
        //String withoutSpaces=read.replace(" ","");

       // return withoutSpaces;
    }

    private static URL getURL(String get_data_from) {

        if(get_data_from==null)
        {
            return null;
        }
        else {
            URL url = null;
            try {
                url = new URL(get_data_from);
            } catch (MalformedURLException e) {
                System.out.println("Error in creating url " + e);
            }
            return url;
        }
    }
}
