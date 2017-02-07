package com.aqif.tweetssearcher.searcher.search.viewmodel;

import android.support.v7.widget.SearchView;

import com.aqif.tweetssearcher.searcher.search.Tweet;
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
    private ITweetsSearchViewModelObservable mTweetsDataChangeObservable;
    private ITweetsSearchModel mTweetsSearchModel;

    @Inject
    public TweetsSearchViewModel(
            SearchView searchView,
            ITweetsSearchViewModelObservable tweetsDataChangeObservable,
            ITweetsSearchModel tweetsSearchModel) {

        mSearchView = searchView;
        mTweetsDataChangeObservable = tweetsDataChangeObservable;
        mTweetsSearchModel = tweetsSearchModel;
        mTweetsSearchModel.setTweetsSearchModelListener(this);

        mSearchView.setQueryHint("Search #HashTag");
        mSearchView.setClickable(false);

    }

    @Override
    public void OnInitialized()
    {
        mSearchView.setClickable(true);
        mSearchView.setOnQueryTextListener(this);
    }

    @Override
    public void OnTweetsSearchDataUpdated(ArrayList<Tweet> mTweets)
    {
        mTweetsDataChangeObservable.notifyDataChanged(mTweets);
    }

    @Override
    public void ShowErrorMessage(String error)
    {
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
        mTweetsSearchModel.searchTweets(hashtag);
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
//          We do not need # sing in query string to make twiter request
            searchTweets(query.replace("#", ""));
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
