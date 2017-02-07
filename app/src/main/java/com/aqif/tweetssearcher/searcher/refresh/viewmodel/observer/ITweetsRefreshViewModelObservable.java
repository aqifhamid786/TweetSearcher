package com.aqif.tweetssearcher.searcher.refresh.viewmodel.observer;

/**
 * Created by aqifhamid on 2/7/17.
 */

public interface ITweetsRefreshViewModelObservable
{
    void registerTweetsRefreshViewModelObserver(ITweetsRefreshViewModelObserver tweetsRefreshViewModelObserver);
    void unregisterTweetsRefreshViewModelObserver(ITweetsRefreshViewModelObserver tweetsRefreshViewModelObserver);

    void notifyRefresh();
}
