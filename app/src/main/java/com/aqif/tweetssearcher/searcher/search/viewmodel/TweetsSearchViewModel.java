package com.aqif.tweetssearcher.searcher.search.viewmodel;

import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.aqif.tweetssearcher.searcher.recycler.model.TweetModel;
import com.aqif.tweetssearcher.searcher.search.viewmodel.observer.ITweetsSearchViewModelObservable;

import com.aqif.tweetssearcher.searcher.search.model.ITweetsSearchModel;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/6/17.
 */

public class TweetsSearchViewModel implements
        ITweetsSearchViewModel,
        ITweetsSearchModel.ITweetsSearchModelListener,
        SearchView.OnQueryTextListener
{
    private SearchView mSearchView;
    private ProgressBar mProgressBar;

    private ITweetsSearchViewModelObservable mTweetsDataChangeObservable;
    private ITweetsSearchModel mTweetsSearchModel;

    private String mCurrentQuery;
    private boolean mIsInitialized;

    @Inject
    public TweetsSearchViewModel(
            SearchView searchView,
            ProgressBar progressBar,
            ITweetsSearchViewModelObservable tweetsDataChangeObservable,
            ITweetsSearchModel tweetsSearchModel) {

        mSearchView = searchView;
        mProgressBar = progressBar;

        mTweetsDataChangeObservable = tweetsDataChangeObservable;
        mTweetsSearchModel = tweetsSearchModel;
        mTweetsSearchModel.setTweetsSearchModelListener(this);
        mSearchView.setOnQueryTextListener(this);

        mSearchView.setQueryHint("Search #HashTag");


    }

    @Override
    public void OnInitialized()
    {
        mIsInitialized = true;
        if(mCurrentQuery!=null && mCurrentQuery.length()>0)
        {
            searchTweets(mCurrentQuery);
        }
    }

    @Override
    public void OnTweetsSearchDataUpdated(ArrayList<TweetModel> mTweetModels, boolean isLastPage)
    {
        mProgressBar.setVisibility(View.INVISIBLE);
        mTweetsDataChangeObservable.notifyDataChanged(mTweetModels, isLastPage);
    }

    @Override
    public void ShowErrorMessage(String error)
    {
        mProgressBar.setVisibility(View.INVISIBLE);
        // TODO: Show notification.
    }

    @Override
    public ITweetsSearchViewModelObservable getTweetsSearchViewModelObservable()
    {
        return mTweetsDataChangeObservable;
    }

    @Override
    public void searchTweets(String hashtag)
    {
        System.out.println("Search Tweet: "+hashtag);
        mCurrentQuery = hashtag;
        mTweetsDataChangeObservable.notifyDataChanged(new ArrayList<TweetModel>(), false);
        mProgressBar.setVisibility(View.VISIBLE);
        mTweetsSearchModel.searchTweets(hashtag);
    }

    @Override
    public void reloadTweets()
    {
        if(mIsInitialized && mCurrentQuery!=null && mCurrentQuery.length()>0)
            mTweetsSearchModel.searchTweets(mCurrentQuery);
        else
            mTweetsDataChangeObservable.notifyDataChanged(new ArrayList<TweetModel>(), false);
    }

    @Override
    public void loadMoreTweets()
    {
        mTweetsSearchModel.loadMoreTweets();
    }

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        if(query!=null && query.trim().length()>0)
        {
            searchTweets(query.replace("#", "").replace("@","").replace(" ",""));
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        return false;
    }


    /** Injectable Fields Composer
     *
     * We cannot inject private fields. We are composing it into an object so that we can hide and inject it as well.
     *
     * */

    public static class InjectableTweetsSearchViewModelField
    {
        @Inject
        public ITweetsSearchViewModel tweetsSearchViewModel;
    }


}
