package com.aqif.tweetssearcher.searcher.search.viewmodel;

import android.support.v7.widget.SearchView;
import android.view.View;
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

        mSearchView.setQueryHint("Search #HashTag");
        mSearchView.setClickable(false);

    }

    @Override
    public void OnInitialized()
    {
        mSearchView.setClickable(true);
        mSearchView.setOnQueryTextListener(this);
//        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mSearchView.onActionViewCollapsed();
//                EditText searchField = (EditText)
//                        mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
//                searchField.setText("Haji");
//            }
//        });
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
        mCurrentQuery = hashtag;
        mTweetsDataChangeObservable.notifyDataChanged(new ArrayList<TweetModel>(), false);
        mProgressBar.setVisibility(View.VISIBLE);
        mTweetsSearchModel.searchTweets(hashtag);
    }

    @Override
    public void reloadTweets()
    {
        mProgressBar.setVisibility(View.VISIBLE);
        mTweetsSearchModel.searchTweets(mCurrentQuery);
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
//          We do not need # sign in query string to make twiter request
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
