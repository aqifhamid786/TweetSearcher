package com.aqif.tweetssearcher.restapi.twitter.oauth.requesthandler;

import com.aqif.tweetssearcher.restapi.TwitterConstants;
import com.aqif.tweetssearcher.restapi.twitter.oauth.basictoken.ITwitterBasicTokenGenerator;
import com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler.ITwitterOAuthCallbackHandler;
import com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler.ITwitterOAuthCallbackObservable;
import com.aqif.tweetssearcher.restapi.twitter.oauth.responsedao.TwitterOAuthDAO;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class TwitterOAuthRequestHandler implements ITwitterOAuthRequestHandler
{

    private ITwitterBasicTokenGenerator mBasicTokenGenerator;
    private ITwitterOAuthRetrofitService mTwitterOAuthRetrofitService;
    private ITwitterOAuthCallbackHandler mTwitterOAuthCallbackHandler;

    @Inject
    public TwitterOAuthRequestHandler(ITwitterBasicTokenGenerator basicTokenGenerator
            , ITwitterOAuthRetrofitService twitterOAuthRetrofitService
            , ITwitterOAuthCallbackHandler twitterOAuthCallbackHandler)
    {
        mBasicTokenGenerator = basicTokenGenerator;
        mTwitterOAuthRetrofitService = twitterOAuthRetrofitService;
        mTwitterOAuthCallbackHandler = twitterOAuthCallbackHandler;
    }

    @Override
    public ITwitterOAuthCallbackObservable getTwitterOAuthObservable()
    {
        return mTwitterOAuthCallbackHandler;
    }

    @Override
    public void performTwitterOAuth()
    {

        Call<TwitterOAuthDAO> call = mTwitterOAuthRetrofitService.getTwitterAccessToken(
                mBasicTokenGenerator.generate(),
                RequestBody.create(MediaType.parse(TwitterConstants.TwitterOAuthBodyContentType), TwitterConstants.TwitterOAuthBody));
        mTwitterOAuthCallbackHandler.setOAuthCall(call);
        call.enqueue(mTwitterOAuthCallbackHandler);
    }

    @Override
    public void cancelCalls()
    {
        if(mTwitterOAuthCallbackHandler.getOAuthCall()!=null)
        {
            mTwitterOAuthCallbackHandler.getOAuthCall().cancel();
        }
    }

}
