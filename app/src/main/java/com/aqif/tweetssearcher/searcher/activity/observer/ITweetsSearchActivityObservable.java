package com.aqif.tweetssearcher.searcher.activity.observer;

/**
 * Created by aqifhamid on 2/7/17.
 */

public interface ITweetsSearchActivityObservable
{
    void registerOnTweetsSearchActivityObserver(ITweetsSearchActivityObserver tweetsSearchActivityObserver);
    void unregisterOnTweetsSearchActivityObserver(ITweetsSearchActivityObserver tweetsSearchActivityObserver);

    void notifyActivityCreated();
    void notifyMenuCreated();
}
