package com.aqif.tweetssearcher.restapi.twitter.manager;

import com.aqif.tweetssearcher.restapi.twitter.manager.observers.ITwitterApiManagerOAuthObservable;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.ITwitterApiManagerTweetsLoadObservable;

/**
 * Created by aqifhamid on 2/5/17.
 */

public interface ITwitterApiManager
{
    void performOAuth();
    void clear();

    ITwitterApiManagerOAuthObservable getTwitterApiManagerOAuthObservable();
    ITwitterApiManagerTweetsLoadObservable getTwitterApiManagerTweetsLoadObservable();

    boolean searchTweets(String tag);
    boolean loadMoreTweets();
}
