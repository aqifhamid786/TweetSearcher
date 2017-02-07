package com.aqif.tweetssearcher.restapi.twitter.manager.observers;


import java.util.List;
import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/5/17.
 */

public class TwitterApiManagerOAuthObservable implements ITwitterApiManagerOAuthObservable {

    protected List<ITwitterApiManagerOAuthObserver> mTwitterApiManagerOAuthObservers;

    @Inject
    public TwitterApiManagerOAuthObservable(List<ITwitterApiManagerOAuthObserver> twitterApiManagerOAuthObservers)
    {
        mTwitterApiManagerOAuthObservers = twitterApiManagerOAuthObservers;
    }


    @Override
    public void registerTwitterApiManagerOAuthObserver(ITwitterApiManagerOAuthObserver twitterApiManagerOAuthObserver)
    {
        if(!mTwitterApiManagerOAuthObservers.contains(twitterApiManagerOAuthObserver))
        {
            mTwitterApiManagerOAuthObservers.add(twitterApiManagerOAuthObserver);
        }
    }

    @Override
    public void unregisterTwitterApiManagerOAuthObserver(ITwitterApiManagerOAuthObserver twitterApiManagerOAuthObserver)
    {
        if(mTwitterApiManagerOAuthObservers.contains(twitterApiManagerOAuthObserver))
        {
            mTwitterApiManagerOAuthObservers.remove(twitterApiManagerOAuthObserver);
        }
    }

    @Override
    public void notifyOAuthSuccess()
    {
        for(int lop = 0; lop< mTwitterApiManagerOAuthObservers.size(); lop++)
        {
            mTwitterApiManagerOAuthObservers.get(lop).onOAuthSuccess();
        }
    }

    @Override
    public void notifyOAuthFailure(String message)
    {
        for(int lop = 0; lop< mTwitterApiManagerOAuthObservers.size(); lop++)
        {
            mTwitterApiManagerOAuthObservers.get(lop).onOAuthFailure(message);
        }
    }


}
