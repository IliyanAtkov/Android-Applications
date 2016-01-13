package com.example.packman.mylibrary.Models;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@ParseClassName(GlobalConstants.BOOKS_PARSE_OBJ_NAME)
public class Books extends ParseObject {

    public String getTitle(){
        return getString("Title");
    }

    public void setTitle(String title){
        put("Title", title);
    }

    public String getCategory(){
        return getString("Category");
    }

    public void setCategory(String category){
        put("Category", category);
    }

    public String getAuthor(){
        return getString("Author");
    }

    public void setAuthor(String author){
        put("Author", author);
    }

    public String getLanguage(){
        return getString("Language");
    }

    public void setLanguage(String language){
        put("Language",language);
    }

    public String getDescription(){
        return getString("Description");
    }

    public void setDescription(String description){
        put("Description",description);
    }

    public ParseFile getImage(){
        return getParseFile("Image");
    }

    public void setImage(ParseFile image){
        put("Image", image);
    }

    public String getObjectID(){
        return getObjectId();
    }

    public void setObjectID(String objectID){
        put("objectId", objectID);
    }

    public static ParseQuery<Books> getQuery(){
        return ParseQuery.getQuery(Books.class);
    }

    @Override
    public String toString(){
        return getString("Title");
    }
}