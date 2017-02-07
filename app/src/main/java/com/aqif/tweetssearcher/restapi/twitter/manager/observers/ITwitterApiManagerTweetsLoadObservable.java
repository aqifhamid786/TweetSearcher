package com.aqif.tweetssearcher.restapi.twitter.manager.observers;

import com.aqif.tweetssearcher.restapi.twitter.searchtweets.responsedao.TweetsSearchDAO;

/**
 * Created by aqifhamid on 2/5/17.
 */

public interface ITwitterApiManagerTweetsLoadObservable
{
    void registerTwitterApiManagerTweetsLoadObserver(ITwitterApiManagerTweetsLoadObserver twitterApiManagerTweetsLoadObserver);
    void unregisterTwitterApiManagerTweetsLoadObserver(ITwitterApiManagerTweetsLoadObserver twitterApiManagerTweetsLoadObserver);

    void notifyTweetsLoadSuccess(TweetsSearchDAO data, boolean isFirstPageData);
    void notifyTweetsLoadFail(String message);
}
