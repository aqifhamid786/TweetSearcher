package com.aqif.tweetssearcher.searcher.recycler.viewmodel;

import com.aqif.tweetssearcher.searcher.search.Tweet;


import java.util.List;

/**
 * Created by aqifhamid on 2/7/17.
 */

public interface ITweetsRecyclerViewModel
{
    void setRecyclerViewData(List<Tweet> data);
}
