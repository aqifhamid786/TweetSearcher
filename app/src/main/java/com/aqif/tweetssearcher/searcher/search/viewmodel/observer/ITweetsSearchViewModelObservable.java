package com.aqif.tweetssearcher.searcher.search.viewmodel.observer;

import com.aqif.tweetssearcher.searcher.search.Tweet;

import java.util.List;

/**
 * Created by aqifhamid on 2/7/17.
 */

public interface ITweetsSearchViewModelObservable
{
    void registerTweetsSearchViewModelObserver(ITweetsSearchViewModelObserver twitterApiManagerOAuthObserver);
    void unregisterTweetsSearchViewModelObserver(ITweetsSearchViewModelObserver twitterApiManagerOAuthObserver);
    void notifyDataChanged(List<Tweet> tweets);
}
