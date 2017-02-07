package com.aqif.tweetssearcher.restapi.twitter.manager.observers;

import com.aqif.tweetssearcher.restapi.twitter.searchtweets.responsedao.TweetsSearchDAO;

/**
 * Created by aqifhamid on 2/5/17.
 */

public interface ITwitterApiManagerTweetsLoadObserver
{
    void onTweetsLoadSuccess(TweetsSearchDAO data, boolean isFirstPageData);
    void onTweetsLoadFail(String message);

}
