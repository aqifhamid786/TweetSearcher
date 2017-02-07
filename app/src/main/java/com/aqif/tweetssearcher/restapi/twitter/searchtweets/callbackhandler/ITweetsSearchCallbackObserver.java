package com.aqif.tweetssearcher.restapi.twitter.searchtweets.callbackhandler;

import com.aqif.tweetssearcher.restapi.twitter.searchtweets.responsedao.TweetsSearchDAO;

/**
 * Created by aqifhamid on 2/5/17.
 */

public interface ITweetsSearchCallbackObserver
{
    void onTweetsSearchSuccess(TweetsSearchDAO responseData);
    void onTweetsSearchFailed(String message);
}
