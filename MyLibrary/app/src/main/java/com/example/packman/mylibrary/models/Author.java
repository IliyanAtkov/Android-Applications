package com.example.packman.mylibrary.models;

public class Author {
    private String name;
    private String imgUrl;

    public Author(String name, String imgUrl) {
        this.setName(name);
        this.setImgUrl(imgUrl);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}