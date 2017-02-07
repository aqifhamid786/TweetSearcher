package com.aqif.tweetssearcher.restapi.twitter.manager.observers;

import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.responsedao.TweetsSearchDAO;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/5/17.
 */

public class TwitterApiManagerTweetsLoadObservable implements ITwitterApiManagerTweetsLoadObservable
{

    protected List<ITwitterApiManagerTweetsLoadObserver> mTwitterApiManagerTweetsLoadObservers;


    @Inject
    public TwitterApiManagerTweetsLoadObservable(List<ITwitterApiManagerTweetsLoadObserver> twitterApiManagerTweetsLoadObservers)
    {
        mTwitterApiManagerTweetsLoadObservers = twitterApiManagerTweetsLoadObservers;
    }


    @Override
    public void registerTwitterApiManagerTweetsLoadObserver(ITwitterApiManagerTweetsLoadObserver twitterApiManagerTweetsLoadObserver)
    {
        if(!mTwitterApiManagerTweetsLoadObservers.contains(twitterApiManagerTweetsLoadObserver))
        {
            mTwitterApiManagerTweetsLoadObservers.add(twitterApiManagerTweetsLoadObserver);
        }
    }

    @Override
    public void unregisterTwitterApiManagerTweetsLoadObserver(ITwitterApiManagerTweetsLoadObserver twitterApiManagerTweetsLoadObserver)
    {
        if(mTwitterApiManagerTweetsLoadObservers.contains(twitterApiManagerTweetsLoadObserver))
        {
            mTwitterApiManagerTweetsLoadObservers.remove(twitterApiManagerTweetsLoadObserver);
        }
    }


    @Override
    public void notifyTweetsLoadSuccess(TweetsSearchDAO data, boolean isFirstPageData)
    {

        for(int lop = 0; lop< mTwitterApiManagerTweetsLoadObservers.size(); lop++)
        {
            mTwitterApiManagerTweetsLoadObservers.get(lop).onTweetsLoadSuccess(data, isFirstPageData);
        }
    }

    @Override
    public void notifyTweetsLoadFail(String message)
    {
        for(int lop = 0; lop< mTwitterApiManagerTweetsLoadObservers.size(); lop++)
        {
            mTwitterApiManagerTweetsLoadObservers.get(lop).onTweetsLoadFail(message);
        }
    }


}
