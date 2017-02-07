package com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler;

import com.aqif.tweetssearcher.restapi.TwitterConstants;
import com.aqif.tweetssearcher.restapi.twitter.oauth.responsedao.TwitterOAuthDAO;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aqifhamid on 2/5/17.
 */

public class TwitterOAuthCallbackHandler implements ITwitterOAuthCallbackHandler
{

    private List<ITwitterOAuthCallbackObserver> mTwitterOAuthCallbackObservers;
    private Call<TwitterOAuthDAO> mCurrentCall;

    @Inject
    public TwitterOAuthCallbackHandler(List<ITwitterOAuthCallbackObserver> twitterOAutherCallbackObservers)
    {
        mTwitterOAuthCallbackObservers = twitterOAutherCallbackObservers;
    }

    @Override
    public void registerTwitterOAuthCallbackObserver(ITwitterOAuthCallbackObserver twitterOAuthCallbackObserver)
    {
        if(!mTwitterOAuthCallbackObservers.contains(twitterOAuthCallbackObserver))
        {
            mTwitterOAuthCallbackObservers.add(twitterOAuthCallbackObserver);
        }
    }

    @Override
    public void unregisterTwitterOAuthCallbackObservable(ITwitterOAuthCallbackObserver twitterOAuthCallbackObserver)
    {
        if(mTwitterOAuthCallbackObservers.contains(twitterOAuthCallbackObserver))
        {
            mTwitterOAuthCallbackObservers.remove(twitterOAuthCallbackObserver);
        }
    }

    @Override
    public void onResponse(Call<TwitterOAuthDAO> call, Response<TwitterOAuthDAO> response)
    {

        mCurrentCall = null;
        if(response.body()!=null)
        {

            for(int lop = 0; lop< mTwitterOAuthCallbackObservers.size(); lop++)
            {
                mTwitterOAuthCallbackObservers.get(lop).onTwitterOAuthSuccess(response.body().getAccessToken());
            }
        }
        else
        {
            // We received response from server. But there is some internal server error.
            for(int lop = 0; lop< mTwitterOAuthCallbackObservers.size(); lop++)
            {
                mTwitterOAuthCallbackObservers.get(lop).onTwitterOAuthFailed(TwitterConstants.TwitterOAuthFailMessage);
            }
        }

    }

    @Override
    public void onFailure(Call<TwitterOAuthDAO> call, Throwable t)
    {
        mCurrentCall = null;
        for(int lop = 0; lop< mTwitterOAuthCallbackObservers.size(); lop++)
        {
            mTwitterOAuthCallbackObservers.get(lop).onTwitterOAuthFailed(TwitterConstants.TwitterOAuthFailMessage);
        }
    }


    @Override
    public Call<TwitterOAuthDAO> getOAuthCall()
    {
        return mCurrentCall;
    }

    @Override
    public void setOAuthCall(Call<TwitterOAuthDAO> call)
    {
        mCurrentCall = call;
    }
}
