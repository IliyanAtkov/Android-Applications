package com.example.packman.mylibrary.models;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.Serializable;
import java.util.Date;

@ParseClassName(GlobalConstants.AUTHORS_PARSE_TABLE_NAME)
public class Authors extends ParseObject implements Serializable {

    public String getName() {
        return getString("Name");
    }

    public void setName(String name) {
        put("Name", name);
    }

    public String getImgUrl() {
        return getString("Img_url");
    }

    public void setImgUrl(String imgUrl) {
        put("Img_url", imgUrl);
    }

    public String getBiography() {
        return getString("Biography");
    }

    public void setBiography(String biography) {
        put("Biography", biography);
    }

    public Date getBorn() {
        return getDate("Born");
    }

    public void setBorn(Date born) {
        put("Born", born);
    }

    public Date getDied() {
        return getDate("Died");
    }

    public void setDied(Date died) {
        put("Died", died);
    }

    public String getNationality() {
        return getString("Nationality");
    }

    public void setNationality(String nationality) {
        put("Nationality", nationality);
    }

    public String getObjectID() {
        return getObjectId();
    }

    public void setObjectID(String objectID) {
        put("objectId", objectID);
    }

    public static ParseQuery<Authors> getQuery() {
        return ParseQuery.getQuery(Authors.class);
    }

    @Override
    public String toString() {
        return getString("Name");
    }
}
