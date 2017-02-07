package com.aqif.tweetssearcher.searcher.swiperefresh.viewmodel.observer;

/**
 * Created by aqifhamid on 2/7/17.
 */

public interface ITweetsSwipeRefreshViewModelObservable
{
    void registerTweetsSwipeRefreshViewModelObserver(ITweetsSwipeRefreshViewModelObserver tweetsSwipeRefreshViewModelObserver);
    void unregisterTweetsSwipeRefreshViewModelObserver(ITweetsSwipeRefreshViewModelObserver tweetsSwipeRefreshViewModelObserver);

    void notifySwipeRefresh();
}
