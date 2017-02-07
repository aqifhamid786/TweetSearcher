package com.aqif.tweetssearcher.restapi.twitter.searchtweets.requesthandler.dagger;

import com.aqif.tweetssearcher.restapi.TwitterConstants;
import com.aqif.tweetssearcher.restapi.twitter.searchtweets.callbackhandler.ITweetsSearchCallbackHandler;
import com.aqif.tweetssearcher.restapi.twitter.searchtweets.requesthandler.ITweetsSearchRequestHandler;
import com.aqif.tweetssearcher.restapi.twitter.searchtweets.requesthandler.ITweetsSearchRetrofitService;
import com.aqif.tweetssearcher.restapi.twitter.searchtweets.requesthandler.TweetsSearchRequestHandler;

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
public class
TweetsSearchRequestHandlerModule {

    @Provides
    ITweetsSearchRequestHandler provideTweetsSearchRequestHandler(ITweetsSearchRetrofitService tweetsSearchRetrofitService
            , ITweetsSearchCallbackHandler tweetsSearchCallbackHandler)
    {
        return new TweetsSearchRequestHandler(tweetsSearchRetrofitService, tweetsSearchCallbackHandler);
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

}
