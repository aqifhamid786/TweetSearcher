package com.aqif.tweetssearcher.restapi.twitter.tweetssearch.callbackhandler;

import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.responsedao.TweetsSearchDAO;

/**
 * Created by aqifhamid on 2/5/17.
 */

public interface ITweetsSearchCallbackObserver
{
    void onTweetsSearchSuccess(TweetsSearchDAO responseData);
    void onTweetsSearchFailed(String message);
}
