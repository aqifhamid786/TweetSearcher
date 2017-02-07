package com.aqif.tweetssearcher.restapi.twitter.oauth.basictoken.dagger;

import com.aqif.tweetssearcher.restapi.twitter.oauth.basictoken.ITwitterBasicTokenGenerator;

import dagger.Component;

/**
 * Created by aqifhamid on 2/5/17.
 */

@Component(modules = {TwitterBasicTokenModule.class})
public interface TwitterBasicTokenComponent
{
    ITwitterBasicTokenGenerator getTwitterTokenGenerator();
}
