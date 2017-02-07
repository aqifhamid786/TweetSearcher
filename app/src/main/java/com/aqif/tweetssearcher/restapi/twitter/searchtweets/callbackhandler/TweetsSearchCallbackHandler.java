package com.aqif.tweetssearcher.restapi.twitter.searchtweets.callbackhandler;

import android.util.Log;

import com.aqif.tweetssearcher.restapi.TwitterConstants;
import com.aqif.tweetssearcher.restapi.twitter.searchtweets.responsedao.TweetsSearchDAO;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aqifhamid on 2/5/17.
 */

public class TweetsSearchCallbackHandler implements ITweetsSearchCallbackHandler
{

    Call<TweetsSearchDAO> mSearchCall;
    private List<ITweetsSearchCallbackObserver> mTweetsSearchCallbackObservers;

    @Inject
    public TweetsSearchCallbackHandler(List<ITweetsSearchCallbackObserver> tweetsSearcherCallbackObservers)
    {
        mTweetsSearchCallbackObservers = tweetsSearcherCallbackObservers;
    }

    @Override
    public void registerTweetsSearchCallbackObserver(ITweetsSearchCallbackObserver tweetsSearcherCallbackObservers)
    {
        if(!mTweetsSearchCallbackObservers.contains(tweetsSearcherCallbackObservers))
        {
            mTweetsSearchCallbackObservers.add(tweetsSearcherCallbackObservers);
        }
    }

    @Override
    public void unregisterTweetsSearchCallbackObservable(ITweetsSearchCallbackObserver tweetsSearcherCallbackObservers)
    {
        if(mTweetsSearchCallbackObservers.contains(tweetsSearcherCallbackObservers))
        {
            mTweetsSearchCallbackObservers.remove(tweetsSearcherCallbackObservers);
        }
    }

    @Override
    public void onResponse(Call<TweetsSearchDAO> call, Response<TweetsSearchDAO> response)
    {
        mSearchCall = null;

        Log.e("onResponse", "on response "+response.body().getStatuses().size()+"");

        if(response.body()!=null)
        {
            for(int lop = 0; lop< mTweetsSearchCallbackObservers.size(); lop++)
            {
                mTweetsSearchCallbackObservers.get(lop).onTweetsSearchSuccess(response.body());
            }
        }
        else
        {
            for(int lop = 0; lop< mTweetsSearchCallbackObservers.size(); lop++)
            {
                mTweetsSearchCallbackObservers.get(lop).onTweetsSearchFailed(TwitterConstants.TwiiterOAuthFailMessage);
            }
        }
    }

    @Override
    public void onFailure(Call<TweetsSearchDAO> call, Throwable t)
    {
        Log.e("onResponse", "on faile "+t.getMessage());
        mSearchCall = null;
        for(int lop = 0; lop< mTweetsSearchCallbackObservers.size(); lop++)
        {
            mTweetsSearchCallbackObservers.get(lop).onTweetsSearchFailed(TwitterConstants.TwiiterOAuthFailMessage);
        }
    }

    @Override
    public void setSearchCall(Call<TweetsSearchDAO> call)
    {
        mSearchCall = call;
    }

    @Override
    public Call<TweetsSearchDAO> getSearchCall()
    {
        return mSearchCall;
    }
}
