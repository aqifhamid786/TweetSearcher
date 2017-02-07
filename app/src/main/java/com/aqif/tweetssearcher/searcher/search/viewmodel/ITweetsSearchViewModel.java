package com.aqif.tweetssearcher.searcher.search.viewmodel;

import com.aqif.tweetssearcher.searcher.search.viewmodel.observer.ITweetsSearchViewModelObservable;

/**
 * Created by aqifhamid on 2/7/17.
 */

public interface ITweetsSearchViewModel
{
    ITweetsSearchViewModelObservable getTweetsSearchViewModelObservable();
    void searchTweets(String hashtag);
    void loadMoreTweets();

}
