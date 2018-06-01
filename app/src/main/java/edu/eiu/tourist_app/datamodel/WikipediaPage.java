package edu.eiu.tourist_app.datamodel;

import com.google.gson.annotations.SerializedName;

public class WikipediaPage {

    @SerializedName("pageid")
    private long pageId;

    @SerializedName("title")
    private String title;

    @SerializedName("index")
    private int index;

    @SerializedName("thumbnail")
    private WikipediaImage thumbnail;

    @SerializedName("pageimage")
    private String pageImage;

    public long getPageId() {
        return pageId;
    }

    public void setPageId(long pageId) {
        this.pageId = pageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public WikipediaImage getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(WikipediaImage thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPageImage() {
        return pageImage;
    }

    public void setPageImage(String pageImage) {
        this.pageImage = pageImage;
    }
}
