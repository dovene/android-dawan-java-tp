package com.dov.calculator.webservice;

public class Photo {
    private Integer id;
    private String title;
    private String thumbnailUrl;
    private String url;

    public Photo(Integer id, String title, String thumbnailUrl, String url) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.url = url;
    }
    // getters et setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
