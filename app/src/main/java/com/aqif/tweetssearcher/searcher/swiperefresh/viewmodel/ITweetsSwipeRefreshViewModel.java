package com.aqif.tweetssearcher.searcher.swiperefresh.viewmodel;

import com.aqif.tweetssearcher.searcher.swiperefresh.viewmodel.observer.ITweetsSwipeRefreshViewModelObservable;

/**
 * Created by aqifhamid on 2/7/17.
 */

public interface ITweetsSwipeRefreshViewModel
{
    boolean isLoading();
    ITweetsSwipeRefreshViewModelObservable getTweetsSwipeRefreshViewModelObservable();

}
