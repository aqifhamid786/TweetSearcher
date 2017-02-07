
package com.aqif.tweetssearcher.restapi.twitter.tweetssearch.responsedao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TweetsSearchDAO {


    @SerializedName("statuses")
    @Expose
    private List<Status> statuses = null;

    @SerializedName("search_metadata")
    @Expose
    private SearchMetadata searchMetadata;

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    public SearchMetadata getSearchMetadata() {
        return searchMetadata;
    }

    public void setSearchMetadata(SearchMetadata searchMetadata) {
        this.searchMetadata = searchMetadata;
    }

}
