package com.aqif.tweetssearcher.searcher.activity.observer;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/7/17.
 */

public class TweetsSearchActivityObservable implements ITweetsSearchActivityObservable
{


    List<ITweetsSearchActivityObserver> mTweetsSearchActivityObservers;

    @Inject
    public TweetsSearchActivityObservable(ArrayList<ITweetsSearchActivityObserver> tweetsSearchActivityObservers)
    {
        mTweetsSearchActivityObservers = tweetsSearchActivityObservers;
    }

    @Override
    public void registerOnTweetsSearchActivityObserver(ITweetsSearchActivityObserver tweetsSearchActivityObserver)
    {
        if(!mTweetsSearchActivityObservers.contains(tweetsSearchActivityObserver))
        {
            mTweetsSearchActivityObservers.add(tweetsSearchActivityObserver);
        }
    }

    @Override
    public void unregisterOnTweetsSearchActivityObserver(ITweetsSearchActivityObserver tweetsSearchActivityObserver)
    {
        if(mTweetsSearchActivityObservers.contains(tweetsSearchActivityObserver))
        {
            mTweetsSearchActivityObservers.remove(tweetsSearchActivityObserver);
        }
    }

    @Override
    public void notifyActivityCreateCalled(AppCompatActivity activity)
    {
        for(int lop=0; lop<mTweetsSearchActivityObservers.size(); lop++)
        {
            mTweetsSearchActivityObservers.get(lop).onActivityCreateCalled(activity);
        }
    }

    @Override
    public void notifyActivityDestroyCalled()
    {
        for(int lop=0; lop<mTweetsSearchActivityObservers.size(); lop++)
        {
            mTweetsSearchActivityObservers.get(lop).onActivityDestroyCalled();
        }
    }

    @Override
    public void notifyBackPressed()
    {
        for(int lop=0; lop<mTweetsSearchActivityObservers.size(); lop++)
        {
            mTweetsSearchActivityObservers.get(lop).onBackPressed();
        }
    }

    @Override
    public void notifyMenuCreated(Menu menu)
    {
        for(int lop=0; lop<mTweetsSearchActivityObservers.size(); lop++)
        {
            mTweetsSearchActivityObservers.get(lop).onMenuCreated(menu);
        }
    }
}
