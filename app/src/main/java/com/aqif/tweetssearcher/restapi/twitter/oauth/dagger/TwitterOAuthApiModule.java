package com.aqif.tweetssearcher.restapi.twitter.oauth.dagger;

import com.aqif.tweetssearcher.restapi.TwitterConstants;
import com.aqif.tweetssearcher.restapi.twitter.oauth.basictoken.ITwitterBasicTokenEncoder;
import com.aqif.tweetssearcher.restapi.twitter.oauth.basictoken.ITwitterBasicTokenGenerator;
import com.aqif.tweetssearcher.restapi.twitter.oauth.basictoken.TwitterBasicTokenEncoder;
import com.aqif.tweetssearcher.restapi.twitter.oauth.basictoken.TwitterBasicTokenGenerator;
import com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler.ITwitterOAuthCallbackHandler;
import com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler.ITwitterOAuthCallbackObserver;
import com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler.TwitterOAuthCallbackHandler;
import com.aqif.tweetssearcher.restapi.twitter.oauth.requesthandler.ITwitterOAuthRequestHandler;
import com.aqif.tweetssearcher.restapi.twitter.oauth.requesthandler.ITwitterOAuthRetrofitService;
import com.aqif.tweetssearcher.restapi.twitter.oauth.requesthandler.TwitterOAuthRequestHandler;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Module
public class TwitterOAuthApiModule
{
    @Provides
    ITwitterBasicTokenGenerator provideTwitterBasicTokenGenerator()
    {
        return new TwitterBasicTokenGenerator(new TwitterBasicTokenEncoder(TwitterConstants.TwitterConsumerKey, TwitterConstants.TwitterConsumerSecret));
    }

    @Provides
    public ITwitterOAuthCallbackHandler provideTwitterOAuthCallbackHandler()
    {
        return new TwitterOAuthCallbackHandler(new ArrayList<ITwitterOAuthCallbackObserver>());
    }

    @Provides
    ITwitterOAuthRetrofitService provideTwitterOAuthRetrofitService()
    {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(httpLoggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TwitterConstants.TwitterBaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        return retrofit.create(ITwitterOAuthRetrofitService.class);
    }

    @Provides
    ITwitterOAuthRequestHandler provideTwitterOAuthHandler(ITwitterBasicTokenGenerator basicTokenGenerator
            , ITwitterOAuthRetrofitService twitterOAuthRetrofitService
            , ITwitterOAuthCallbackHandler twitterOAuthCallbackHandler)
    {
        return new TwitterOAuthRequestHandler(basicTokenGenerator, twitterOAuthRetrofitService, twitterOAuthCallbackHandler);
    }

}
