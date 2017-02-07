package com.aqif.tweetssearcher.searcher.search.viewmodel.observer;

import com.aqif.tweetssearcher.searcher.recycler.model.TweetModel;

import java.util.List;

/**
 * Created by aqifhamid on 2/7/17.
 */

public interface ITweetsSearchViewModelObserver
{
    void onDataChanged(List<TweetModel> tweetModels, boolean isLastPage);
    void onClearData();
    void onFailure();
}
