package com.aqif.tweetssearcher.restapi.twitter.searchtweets.requesthandler;


import com.aqif.tweetssearcher.restapi.TwitterConstants;
import com.aqif.tweetssearcher.restapi.twitter.searchtweets.callbackhandler.ITweetsSearchCallbackHandler;
import com.aqif.tweetssearcher.restapi.twitter.searchtweets.callbackhandler.ITweetsSearchCallbackObservable;
import com.aqif.tweetssearcher.restapi.twitter.searchtweets.responsedao.TweetsSearchDAO;

import javax.inject.Inject;

import retrofit2.Call;

public class TweetsSearchRequestHandler implements ITweetsSearchRequestHandler
{

    private ITweetsSearchRetrofitService mTweetsSearchRetrofitService;
    private ITweetsSearchCallbackHandler mTweetsSearchCallbackHandler;


    @Inject
    public TweetsSearchRequestHandler( ITweetsSearchRetrofitService tweetsSearchRetrofitService
            , ITweetsSearchCallbackHandler tweetsSearchCallbackHandler)
    {
        mTweetsSearchRetrofitService = tweetsSearchRetrofitService;
        mTweetsSearchCallbackHandler = tweetsSearchCallbackHandler;
    }

    @Override
    public ITweetsSearchCallbackObservable getTweetsSearchCallbackObservable()
    {
        return mTweetsSearchCallbackHandler;
    }


    @Override
    public void searchTweets(String accessToken, String hashtag)
    {
        if(mTweetsSearchCallbackHandler.getSearchCall()!=null)
        {
            mTweetsSearchCallbackHandler.getSearchCall().cancel();
        }
        Call<TweetsSearchDAO> call = mTweetsSearchRetrofitService.searchTweets("Bearer "+accessToken, hashtag, TwitterConstants.TweetsPerRequestCount);
        mTweetsSearchCallbackHandler.setSearchCall(call);
        call.enqueue(mTweetsSearchCallbackHandler);
    }

    @Override
    public void loadMoreTweets(String accessToken, String hashtag, String maxId)
    {
        if(mTweetsSearchCallbackHandler.getSearchCall()!=null)
        {
            mTweetsSearchCallbackHandler.getSearchCall().cancel();
        }

        Call<TweetsSearchDAO> call = mTweetsSearchRetrofitService.loadMoreTweets("Bearer "+accessToken, hashtag, maxId,TwitterConstants.TweetsPerRequestCount);
        mTweetsSearchCallbackHandler.setSearchCall(call);
        call.enqueue(mTweetsSearchCallbackHandler);
    }

    @Override
    public void cancelCalls()
    {
        if(mTweetsSearchCallbackHandler.getSearchCall()!=null)
        {
            mTweetsSearchCallbackHandler.getSearchCall().cancel();
        }
    }

}
