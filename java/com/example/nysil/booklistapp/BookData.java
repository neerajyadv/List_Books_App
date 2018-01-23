package com.example.nysil.booklistapp;


public class BookData {

    private String bookName;
    private String authorName;
    BookData( String bookName, String authorName)
    {
        this.bookName=bookName;
        this.authorName=authorName;
    }



    public String getBookName()
    {
        return bookName;
    }

    public String getAuthorName()
    {
        return authorName;
    }

}
