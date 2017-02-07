
package com.aqif.tweetssearcher.restapi.twitter.searchtweets.responsedao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("created_at")
    @Expose
    private String createdAt;


    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("entities")
    @Expose
    private Entities entities;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("retweet_count")
    @Expose
    private Integer retweetCount;

    @SerializedName("favorite_count")
    @Expose
    private Integer favoriteCount;

    @SerializedName("lang")
    @Expose
    private String lang;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(Integer retweetCount) {
        this.retweetCount = retweetCount;
    }

    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

}
