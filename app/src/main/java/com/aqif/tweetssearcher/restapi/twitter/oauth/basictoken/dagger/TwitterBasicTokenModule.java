package com.aqif.tweetssearcher.restapi.twitter.oauth.basictoken.dagger;

import com.aqif.tweetssearcher.restapi.TwitterConstants;
import com.aqif.tweetssearcher.restapi.twitter.oauth.basictoken.ITwitterBasicTokenEncoder;
import com.aqif.tweetssearcher.restapi.twitter.oauth.basictoken.ITwitterBasicTokenGenerator;
import com.aqif.tweetssearcher.restapi.twitter.oauth.basictoken.TwitterBasicTokenEncoder;
import com.aqif.tweetssearcher.restapi.twitter.oauth.basictoken.TwitterBasicTokenGenerator;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Module
public class TwitterBasicTokenModule
{

    @Provides
    ITwitterBasicTokenEncoder provideTwitterBasicTokenEncoder()
    {
        return new TwitterBasicTokenEncoder(TwitterConstants.TwitterConsumerKey, TwitterConstants.TwitterConsumerSecret);
    }

    @Provides
    ITwitterBasicTokenGenerator provideTwitterBasicTokenGenerator(ITwitterBasicTokenEncoder twitterBasicTokenEncoder)
    {
        return new TwitterBasicTokenGenerator(twitterBasicTokenEncoder);
    }

}
