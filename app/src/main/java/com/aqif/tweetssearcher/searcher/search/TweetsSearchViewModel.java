package com.aqif.tweetssearcher.searcher.search;

import com.aqif.tweetssearcher.restapi.twitter.manager.ITwitterApiManager;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.ITwitterApiManagerOAuthObserver;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.ITwitterApiManagerTweetsLoadObserver;
import com.aqif.tweetssearcher.restapi.twitter.searchtweets.responsedao.Status;
import com.aqif.tweetssearcher.restapi.twitter.searchtweets.responsedao.TweetsSearchDAO;
import com.aqif.tweetssearcher.searcher.search.observer.ITweetsDataChangeObservable;

import java.util.ArrayList;

/**
 * Created by aqifhamid on 2/6/17.
 */

public class TweetsSearchViewModel implements
        ITwitterApiManagerOAuthObserver,
        ITwitterApiManagerTweetsLoadObserver,
        ITweetsSearchViewModel
{

    private ITwitterApiManager mTwitterApiManager;
    private ITweetsDataChangeObservable mTweetsDataChangeObservable;

    private boolean isReadyToFetchTweets;
    private ArrayList<Tweet> mTweets;

    public TweetsSearchViewModel(ITweetsDataChangeObservable tweetsDataChangeObservable, ITwitterApiManager twitterApiManager)
    {
        mTweetsDataChangeObservable = tweetsDataChangeObservable;
        mTwitterApiManager = twitterApiManager;

        mTwitterApiManager.getTwitterApiManagerOAuthObservable().registerTwitterApiManagerOAuthObserver(this);
        mTwitterApiManager.getTwitterApiManagerTweetsLoadObservable().registerTwitterApiManagerTweetsLoadObserver(this);
        mTwitterApiManager.performOAuth();

        mTweets = new ArrayList<>();

    }

    @Override
    public void onOAuthSuccess()
    {
        isReadyToFetchTweets = true;
    }

    @Override
    public void onOAuthFailure(String message)
    {
        isReadyToFetchTweets = false;
        // TODO: show notification
    }


    @Override
    public void onTweetsLoadSuccess(TweetsSearchDAO data, boolean isFirstPageData)
    {
        if(isFirstPageData)
        {
            mTweets.clear();
        }
        for(int lop=0; lop<data.getStatuses().size(); lop++)
        {
            Status status = data.getStatuses().get(lop);
            mTweets.add(new Tweet(status.getUser().getName(), "@"+status.getUser().getScreenName(), status.getText(), status.getRetweetCount(), status.getFavoriteCount()));
        }
        mTweetsDataChangeObservable.notifyDataChanged(mTweets);
    }

    @Override
    public void onTweetsLoadFail(String message)
    {
        // TODO: show notification
    }


    @Override
    public void searchTweets(String hashtag)
    {
        if(isReadyToFetchTweets && hashtag!=null && hashtag.length()>0)
        {
            mTwitterApiManager.searchTweets(hashtag);
        }
        else
        {
            // TODO: show notification
        }
    }

    @Override
    public void loadMoreTweets()
    {
        if(isReadyToFetchTweets)
        {
            mTwitterApiManager.loadMoreTweets();
        }
        else
        {
            // TODO: show notification
        }
    }

}
