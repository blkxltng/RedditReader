package com.blkxltng.redditreader.reddit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by firej on 10/13/2017.
 */

public class Child {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("data")
    @Expose
    private SubRedditPost data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public SubRedditPost getData() {
        return data;
    }

    public void setData(SubRedditPost data) {
        this.data = data;
    }
}
