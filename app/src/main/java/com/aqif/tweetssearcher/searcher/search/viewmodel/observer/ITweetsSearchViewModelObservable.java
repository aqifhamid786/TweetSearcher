package com.aqif.tweetssearcher.searcher.search.viewmodel.observer;

import com.aqif.tweetssearcher.searcher.recycler.model.TweetModel;

import java.util.List;

/**
 * Created by aqifhamid on 2/7/17.
 */

public interface ITweetsSearchViewModelObservable
{
    void registerTweetsSearchViewModelObserver(ITweetsSearchViewModelObserver tweetsSearchViewModelObserver);
    void unregisterTweetsSearchViewModelObserver(ITweetsSearchViewModelObserver tweetsSearchViewModelObserver);

    void notifyDataChanged(List<TweetModel> tweetModels, boolean isLastPage);
    void notifyClearData();
    void notifyFailure();
}
