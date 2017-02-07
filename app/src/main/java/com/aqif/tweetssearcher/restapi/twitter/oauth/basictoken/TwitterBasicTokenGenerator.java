package com.aqif.tweetssearcher.restapi.twitter.oauth.basictoken;


import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/4/17.
 */

public class TwitterBasicTokenGenerator implements ITwitterBasicTokenGenerator
{
    private ITwitterBasicTokenEncoder mTwitterBasicTokenEncoder;

    @Inject
    public TwitterBasicTokenGenerator(ITwitterBasicTokenEncoder twitterBasicTokenEncoder)
    {
        mTwitterBasicTokenEncoder = twitterBasicTokenEncoder;
    }

    @Override
    public String generate()
    {
        return "Basic "+mTwitterBasicTokenEncoder.encode();
    }
}
