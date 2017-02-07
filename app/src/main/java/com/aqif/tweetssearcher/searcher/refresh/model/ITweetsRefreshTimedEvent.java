package com.aqif.tweetssearcher.searcher.refresh.model;

/**
 * Created by aqifhamid on 2/8/17.
 */

public interface ITweetsRefreshTimedEvent extends Runnable
{


    void setTweetsRefreshTimedEventListener(ITweetsRefreshTimedEventListener tweetsRefreshTimedEventListener);

    /**
     *
     * Timed event Listener
     *
     */

    interface ITweetsRefreshTimedEventListener
    {
        void onTimedEvent();
    }

}
