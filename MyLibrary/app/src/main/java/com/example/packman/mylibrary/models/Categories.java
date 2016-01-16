package com.example.packman.mylibrary.models;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.Serializable;

@ParseClassName(GlobalConstants.CATEGORIES_PARSE_TABLE_NAME)
public class Categories extends ParseObject implements Serializable {

    public String getTitle(){
        return getString("Title");
    }

    public void setTitle(String title){
        put("Title", title);
    }

    public String getObjectID() {
        return getObjectId();
    }

    public void setObjectID(String objectID) {
        put("objectId", objectID);
    }

    public static ParseQuery<Categories> getQuery() {
        return ParseQuery.getQuery(Categories.class);
    }
    @Override
    public String toString() {
        return getString("Title");
    }
}
