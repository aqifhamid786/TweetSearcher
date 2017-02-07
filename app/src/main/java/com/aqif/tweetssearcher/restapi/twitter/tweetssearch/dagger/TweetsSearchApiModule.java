package com.aqif.tweetssearcher.restapi.twitter.tweetssearch.dagger;

import com.aqif.tweetssearcher.restapi.TwitterConstants;
import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.callbackhandler.ITweetsSearchCallbackHandler;
import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.callbackhandler.ITweetsSearchCallbackObserver;
import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.callbackhandler.TweetsSearchCallbackHandler;
import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.requesthandler.ITweetsSearchRequestHandler;
import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.requesthandler.ITweetsSearchRetrofitService;
import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.requesthandler.TweetsSearchRequestHandler;

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
public class TweetsSearchApiModule
{

    @Provides
    public ITweetsSearchCallbackHandler provideTweetsSearchCallbackHandler()
    {
        return new TweetsSearchCallbackHandler(new ArrayList<ITweetsSearchCallbackObserver>());
    }

    @Provides
    ITweetsSearchRetrofitService provideTweetsSearchRetrofitService()
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
        return retrofit.create(ITweetsSearchRetrofitService.class);
    }

    @Provides
    ITweetsSearchRequestHandler provideTweetsSearchRequestHandler(ITweetsSearchRetrofitService tweetsSearchRetrofitService
            , ITweetsSearchCallbackHandler tweetsSearchCallbackHandler)
    {
        return new TweetsSearchRequestHandler(tweetsSearchRetrofitService, tweetsSearchCallbackHandler);
    }
}
