package com.aqif.tweetssearcher.searcher.search.observer;

import com.aqif.tweetssearcher.searcher.search.Tweet;

import java.util.List;

/**
 * Created by aqifhamid on 2/7/17.
 */

public interface ITweetsDataChangeObserver
{
    void onDataChanged(List<Tweet> tweets);
}
