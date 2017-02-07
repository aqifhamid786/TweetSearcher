package com.aqif.tweetssearcher.searcher.recycler.viewmodel.observer;

/**
 * Created by aqifhamid on 2/7/17.
 */

public interface ITweetsRecyclerViewModelObservable
{
    void registerRecyclerViewModelObserver(ITweetsRecyclerViewModelObserver recyclerViewModelObserver);
    void unregisterRecyclerViewModelObserver(ITweetsRecyclerViewModelObserver recyclerViewModelObserver);

    void notifyLoadMoreTweetsData();
    void notifyLoadTweets(String hashtag);


}
