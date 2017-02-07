package com.aqif.tweetssearcher.searcher.refresh.viewmodel;

import com.aqif.tweetssearcher.searcher.refresh.viewmodel.observer.ITweetsRefreshViewModelObservable;

/**
 * Created by aqifhamid on 2/7/17.
 */

public interface ITweetsRefreshViewModel
{
    void hideLoader();
    boolean isLoading();

    void reset();

    void onActivityResumeCalled();
    void onActiviyStopCalled();
    void onActiviyDestroyCalled();

    ITweetsRefreshViewModelObservable getTweetsRefreshViewModelObservable();

}
