package com.aqif.tweetssearcher.searcher.refresh.model;

import android.os.Handler;

import com.aqif.tweetssearcher.R;
import com.aqif.tweetssearcher.searcher.refresh.model.ITweetsRefreshTimedEvent.ITweetsRefreshTimedEventListener;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/8/17.
 */

public class TweetsRefreshModel implements ITweetsRefreshModel {

    public static final int NO_REFRESH = -1;
    public static final int TWO_MINUTE = 2;
    public static final int FIVE_MINUTE = 5;
    public static final int THIRTY_MINUTE = 30;
    public static final int SIXTY_MINUTE = 60;


    private int mAutoRefreshTime;

    private Handler mTimedEventHandler;
    private ITweetsRefreshTimedEvent mTweetsRefreshTimedEvent;
    private ITweetsRefreshTimedEventListener mTweetsRefreshTimedEventListener;


    @Inject
    public TweetsRefreshModel(Handler timedEventHandler, ITweetsRefreshTimedEvent tweetsRefreshTimedEvent)
    {
        mTimedEventHandler = timedEventHandler;
        mTweetsRefreshTimedEvent = tweetsRefreshTimedEvent;
        mTweetsRefreshTimedEvent.setTweetsRefreshTimedEventListener(this);

        mAutoRefreshTime = TWO_MINUTE;
    }

    @Override
    public void onDrawerOptionSelection(int id)
    {
        if(mAutoRefreshTime!=NO_REFRESH)
        {
            mTimedEventHandler.removeCallbacks(mTweetsRefreshTimedEvent);
        }

        switch (id)
        {
            case R.id.no_refresh:
                mAutoRefreshTime = NO_REFRESH;
                break;
            case R.id.two_seconds:
                mAutoRefreshTime = TWO_MINUTE;
                break;
            case R.id.five_seconds:
                mAutoRefreshTime = FIVE_MINUTE;
                break;
            case R.id.thirty_seconds:
                mAutoRefreshTime = THIRTY_MINUTE;
                break;
            case R.id.one_minute:
                mAutoRefreshTime = SIXTY_MINUTE;
        }

        updateTimedEvent();
    }

    private void updateTimedEvent()
    {
        if(mAutoRefreshTime!=NO_REFRESH)
        {

            mTimedEventHandler.postDelayed(mTweetsRefreshTimedEvent, mAutoRefreshTime*1000);
        }

    }

    @Override
    public void onTimedEvent()
    {
        if(mAutoRefreshTime == NO_REFRESH)
        {
            return;
        }
        if(mTweetsRefreshTimedEventListener!=null)
            mTweetsRefreshTimedEventListener.onTimedEvent();
        updateTimedEvent();
    }

    @Override
    public boolean shouldEnableSwipeRefreshLayout()
    {
        return (mAutoRefreshTime==NO_REFRESH);
    }

    @Override
    public void resetTimedEvent()
    {
        if(mAutoRefreshTime!=NO_REFRESH)
        {
            mTimedEventHandler.removeCallbacks(mTweetsRefreshTimedEvent);
            updateTimedEvent();
        }
    }

    @Override
    public void stopTimedEvent()
    {
        if(mAutoRefreshTime!=NO_REFRESH)
        {
            mTimedEventHandler.removeCallbacks(mTweetsRefreshTimedEvent);
        }
    }

    @Override
    public void resumeTimedEvent()
    {
        updateTimedEvent();
    }


    @Override
    public void setTweetsRefreshTimedEventListener(ITweetsRefreshTimedEventListener tweetsRefreshTimedEventListener)
    {
        mTweetsRefreshTimedEventListener = tweetsRefreshTimedEventListener;
    }


}
