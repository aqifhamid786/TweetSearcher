package com.aqif.tweetssearcher.searcher.refresh.model;

/**
 * Created by aqifhamid on 2/8/17.
 */

public interface ITweetsRefreshModel extends ITweetsRefreshTimedEvent.ITweetsRefreshTimedEventListener
{
    void onDrawerOptionSelection(int id);
    boolean shouldEnableSwipeRefreshLayout();

    void resetTimedEvent();

    void stopTimedEvent();
    void resumeTimedEvent();

    void setTweetsRefreshTimedEventListener(ITweetsRefreshTimedEvent.ITweetsRefreshTimedEventListener tweetsRefreshTimedEventListener);
}
