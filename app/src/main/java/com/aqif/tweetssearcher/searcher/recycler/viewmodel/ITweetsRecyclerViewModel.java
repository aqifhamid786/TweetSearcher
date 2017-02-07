package com.aqif.tweetssearcher.searcher.recycler.viewmodel;

import com.aqif.tweetssearcher.searcher.recycler.viewmodel.observer.ITweetsRecyclerViewModelObservable;
import com.aqif.tweetssearcher.searcher.recycler.model.TweetModel;


import java.util.List;

/**
 * Created by aqifhamid on 2/7/17.
 */

public interface ITweetsRecyclerViewModel
{
    void setRecyclerViewData(List<TweetModel> data, boolean isLastPage);
    void onLoadDataFailed();
    ITweetsRecyclerViewModelObservable getTweetsRecyclerViewModelObserver();
    void onActivityDestroyCalled();

}
