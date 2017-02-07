package com.aqif.tweetssearcher.searcher.search.model;

import com.aqif.tweetssearcher.restapi.twitter.manager.ITwitterApiManager;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.ITwitterApiManagerOAuthObserver;
import com.aqif.tweetssearcher.restapi.twitter.manager.observers.ITwitterApiManagerTweetsLoadObserver;
import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.responsedao.Status;
import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.responsedao.TweetsSearchDAO;
import com.aqif.tweetssearcher.searcher.search.Tweet;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/6/17.
 */

public class TweetsSearchModel implements
        ITweetsSearchModel,
        ITwitterApiManagerOAuthObserver,
        ITwitterApiManagerTweetsLoadObserver
{

    private ITwitterApiManager mTwitterApiManager;
    private ITweetsSearchModelListener mTweetsSearchModelListener;

    private boolean isReadyToFetchTweets;
    private ArrayList<Tweet> mTweets;

    @Inject
    public TweetsSearchModel(ITwitterApiManager twitterApiManager)
    {

        mTwitterApiManager = twitterApiManager;

        mTwitterApiManager.getTwitterApiManagerOAuthObservable().registerTwitterApiManagerOAuthObserver(this);
        mTwitterApiManager.getTwitterApiManagerTweetsLoadObservable().registerTwitterApiManagerTweetsLoadObserver(this);
        mTwitterApiManager.performOAuth();

        mTweets = new ArrayList<>();

    }

    @Override
    public void setTweetsSearchModelListener(ITweetsSearchModelListener tweetsSearchModelListener)
    {
        mTweetsSearchModelListener = tweetsSearchModelListener;
    }

    @Override
    public void onOAuthSuccess()
    {
        isReadyToFetchTweets = true;
        if(mTweetsSearchModelListener!=null)
        {
            mTweetsSearchModelListener.OnInitialized();
        }
    }

    @Override
    public void onOAuthFailure(String message)
    {
        isReadyToFetchTweets = false;
        if(mTweetsSearchModelListener!=null)
        {
            mTweetsSearchModelListener.ShowErrorMessage(message);
        }
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

        if(mTweetsSearchModelListener!=null)
        {
            mTweetsSearchModelListener.OnTweetsSearchDataUpdated(mTweets);
        }

    }

    @Override
    public void onTweetsLoadFail(String message)
    {
        if(mTweetsSearchModelListener!=null)
        {
            mTweetsSearchModelListener.ShowErrorMessage(message);
        }
    }


    @Override
    public boolean searchTweets(String hashtag)
    {
        if(isReadyToFetchTweets && hashtag!=null && hashtag.length()>0)
        {
            mTwitterApiManager.searchTweets(hashtag);
        }
        else
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean loadMoreTweets()
    {
        if(isReadyToFetchTweets)
        {
            mTwitterApiManager.loadMoreTweets();
        }
        else
        {
            return false;
        }
        return true;
    }

}
