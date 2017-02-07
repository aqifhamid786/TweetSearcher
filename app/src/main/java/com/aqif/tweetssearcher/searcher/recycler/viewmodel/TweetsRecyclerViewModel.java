package com.aqif.tweetssearcher.searcher.recycler.viewmodel;

import android.support.v7.widget.RecyclerView;

import com.aqif.tweetssearcher.searcher.recycler.viewmodel.adapter.TweetsRecyclerAdapter;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.observer.ITweetsRecyclerViewModelObservable;
import com.aqif.tweetssearcher.searcher.recycler.model.TweetModel;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.observer.RecyclerViewScrollToEndObserver;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/7/17.
 */

public class TweetsRecyclerViewModel implements
        ITweetsRecyclerViewModel,
        RecyclerViewScrollToEndObserver.IOnLoadMoreRecycleViewDataListner
{

    private RecyclerView mRecyclerView;
    private TweetsRecyclerAdapter mRecyclerViewAdapter;
    private RecyclerViewScrollToEndObserver mRecyclerViewScrollToEndObserver;
    private ITweetsRecyclerViewModelObservable mTweetsRecyclerViewModelObservable;

    boolean mIsLastPageLoaded;

    @Inject
    public TweetsRecyclerViewModel(RecyclerView recyclerView,
                                   TweetsRecyclerAdapter recyclerViewAdapter,
                                   ITweetsRecyclerViewModelObservable tweetsRecyclerViewModelObservable,
                                   RecyclerViewScrollToEndObserver recyclerViewScrollToEndObserver)
    {
        mRecyclerView = recyclerView;
        mRecyclerViewAdapter = recyclerViewAdapter;
        mTweetsRecyclerViewModelObservable = tweetsRecyclerViewModelObservable;

        mRecyclerViewScrollToEndObserver = recyclerViewScrollToEndObserver;
        mRecyclerViewScrollToEndObserver.setOnLoadMoreRecycleViewDataListner(this);

        mRecyclerView.addOnScrollListener(mRecyclerViewScrollToEndObserver);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

    }

    @Override
    public void setRecyclerViewData(List<TweetModel> data, boolean isLastPage)
    {
        mIsLastPageLoaded = isLastPage;
        mRecyclerViewAdapter.setLoadingMoreItem(false);
        mRecyclerViewAdapter.updateData(data);
        mRecyclerViewScrollToEndObserver.setLoadingData(mIsLastPageLoaded);
    }

    @Override
    public void onLoadMoreRecycleViewDataListner(int page, int totalItemsCount, RecyclerView view)
    {
        mRecyclerViewScrollToEndObserver.setLoadingData(true);
        if(!mIsLastPageLoaded)
        {
            mRecyclerView.post(new Runnable()
            {
                @Override
                public void run()
                {
                    mRecyclerViewAdapter.setLoadingMoreItem(true);
                    mTweetsRecyclerViewModelObservable.notifyLoadMoreTweetsData();
                }
            });
        }
    }

    @Override
    public ITweetsRecyclerViewModelObservable getTweetsRecyclerViewModelObserver()
    {
        return mTweetsRecyclerViewModelObservable;
    }

    /** Injectable Fields Composer
     *
     * We cannot inject private fields. We are composing it into an object so that we can hide and inject it as well.
     *
     * */

    public static class InjectableTweetsRecyclerViewModelField
    {
        @Inject
        public ITweetsRecyclerViewModel tweetsRecyclerViewModel;
    }
}
