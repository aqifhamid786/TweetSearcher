package com.aqif.tweetssearcher.searcher.refresh.model;

/**
 * Created by aqifhamid on 2/8/17.
 */

public class TweetsRefreshTimedEvent implements ITweetsRefreshTimedEvent
{

    private ITweetsRefreshTimedEventListener mTweetsRefreshTimedEventListener;

    public TweetsRefreshTimedEvent() {}

    @Override
    public void run()
    {
        if(mTweetsRefreshTimedEventListener!=null)
        {
            mTweetsRefreshTimedEventListener.onTimedEvent();
        }
    }

    @Override
    public void setTweetsRefreshTimedEventListener(ITweetsRefreshTimedEventListener tweetsRefreshTimedEventListener)
    {
        mTweetsRefreshTimedEventListener = tweetsRefreshTimedEventListener;
    }


}
