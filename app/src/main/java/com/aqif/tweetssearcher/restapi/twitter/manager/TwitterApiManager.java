package com.aqif.tweetssearcher.restapi.twitter.manager;

import com.aqif.tweetssearcher.restapi.twitter.manager.nextpageidextractor.INextPageIdExtractor;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.ITwitterApiManagerOAuthObservable;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.ITwitterApiManagerTweetsLoadObservable;
import com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler.ITwitterOAuthCallbackObserver;
import com.aqif.tweetssearcher.restapi.twitter.oauth.requesthandler.ITwitterOAuthRequestHandler;
import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.callbackhandler.ITweetsSearchCallbackObserver;
import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.requesthandler.ITweetsSearchRequestHandler;
import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.responsedao.TweetsSearchDAO;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/5/17.
 */

public class TwitterApiManager implements ITwitterApiManager
        , ITweetsSearchCallbackObserver
        , ITwitterOAuthCallbackObserver
{

    private ITwitterOAuthRequestHandler mTwitterOAuthCallbackHandler;
    private ITweetsSearchRequestHandler mTweetsSearchRequestHandler;
    private ITwitterApiManagerOAuthObservable mTwitterApiManagerOAuthObservable;
    private ITwitterApiManagerTweetsLoadObservable mTwitterApiManagerTweetsLoadObservable;
    private INextPageIdExtractor mNextPageIdExtractor;

    private TweetsSearchDAO mCurrentSearchResults;

    private String mNextPageMaxId;
    private String mAccessToken;
    private boolean isOAuthCompleted;

    @Inject
    public TwitterApiManager(ITwitterOAuthRequestHandler twitterOAuthCallbackHandler
                                , ITweetsSearchRequestHandler tweetsSearchRequestHandler
                                , ITwitterApiManagerOAuthObservable twitterApiManagerOAuthObservable
                                , ITwitterApiManagerTweetsLoadObservable twitterApiManagerTweetsLoadObservable
                                , INextPageIdExtractor nextPageIdExtractor
    )
    {
        mTwitterOAuthCallbackHandler = twitterOAuthCallbackHandler;
        mTweetsSearchRequestHandler = tweetsSearchRequestHandler;
        mTwitterApiManagerOAuthObservable = twitterApiManagerOAuthObservable;
        mTwitterApiManagerTweetsLoadObservable = twitterApiManagerTweetsLoadObservable;
        mNextPageIdExtractor = nextPageIdExtractor;

        mTwitterOAuthCallbackHandler.getTwitterOAuthObservable().registerTwitterOAuthCallbackObserver(TwitterApiManager.this);
        mTweetsSearchRequestHandler.getTweetsSearchCallbackObservable().registerTweetsSearchCallbackObserver(TwitterApiManager.this);

    }

    @Override
    public void performOAuth()
    {
        mTwitterOAuthCallbackHandler.performTwitterOAuth();
    }

    @Override
    public void clear()
    {
        mTwitterOAuthCallbackHandler.getTwitterOAuthObservable().unregisterTwitterOAuthCallbackObservable(TwitterApiManager.this);
        mTweetsSearchRequestHandler.getTweetsSearchCallbackObservable().unregisterTweetsSearchCallbackObservable(TwitterApiManager.this);
        mTwitterOAuthCallbackHandler.cancelCalls();
        mTweetsSearchRequestHandler.cancelCalls();
    }

    @Override
    public ITwitterApiManagerOAuthObservable getTwitterApiManagerOAuthObservable()
    {
        return mTwitterApiManagerOAuthObservable;
    }

    @Override
    public ITwitterApiManagerTweetsLoadObservable getTwitterApiManagerTweetsLoadObservable()
    {
        return mTwitterApiManagerTweetsLoadObservable;
    }

    @Override
    public boolean searchTweets(String tag)
    {
        if(isOAuthCompleted)
        {
            mNextPageMaxId = null;
            mCurrentSearchResults = null;
            mTweetsSearchRequestHandler.searchTweets(mAccessToken, tag);
            return true;
        }
        return false;
    }

    @Override
    public boolean loadMoreTweets()
    {
        if(mCurrentSearchResults!=null)
        {
            String maxId = mNextPageIdExtractor.extract(mCurrentSearchResults.getSearchMetadata().getNextResults());
            if(maxId!=null)
            {
                mNextPageMaxId = maxId;
                mTweetsSearchRequestHandler.loadMoreTweets(mAccessToken, mCurrentSearchResults.getSearchMetadata().getQuery(), mNextPageMaxId);
                return true;
            }
        }
        return false;
    }

    /******** Twitter OAuth  Callbacks *******/
    @Override
    public void onTwitterOAuthSuccess(String accessToken)
    {
        mAccessToken = accessToken;
        isOAuthCompleted = true;
        mTwitterApiManagerOAuthObservable.notifyOAuthSuccess();
    }

    @Override
    public void onTwitterOAuthFailed(String message)
    {
        mAccessToken = null;
        isOAuthCompleted = false;
        mTwitterApiManagerOAuthObservable.notifyOAuthFailure(message);
    }

    /******** Tweets Search Observer Callbacks *******/
    @Override
    public void onTweetsSearchSuccess(TweetsSearchDAO responseData)
    {
        mCurrentSearchResults = responseData;
        mTwitterApiManagerTweetsLoadObservable.notifyTweetsLoadSuccess(responseData, mNextPageMaxId==null);
    }

    @Override
    public void onTweetsSearchFailed(String message)
    {
        mTwitterApiManagerTweetsLoadObservable.notifyTweetsLoadFail(message);
    }

}
