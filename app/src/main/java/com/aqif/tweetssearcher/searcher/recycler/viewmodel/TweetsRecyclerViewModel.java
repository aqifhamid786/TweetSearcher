package com.aqif.tweetssearcher.searcher.recycler.viewmodel;

import android.support.v7.widget.RecyclerView;

import com.aqif.tweetssearcher.searcher.recycler.viewmodel.adapter.TweetsRecyclerAdapter;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.observer.ITweetsRecyclerViewModelObservable;
import com.aqif.tweetssearcher.searcher.recycler.model.TweetModel;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.observer.RecyclerViewScrollToEndObserver;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/7/17.
 */

public class TweetsRecyclerViewModel implements
        ITweetsRecyclerViewModel,
        RecyclerViewScrollToEndObserver.IOnLoadMoreRecycleViewDataListner,
        TweetModel.IOnTweetModelSpannableClicked
{

    private RecyclerView mRecyclerView;
    private TweetsRecyclerAdapter mRecyclerViewAdapter;
    private RecyclerViewScrollToEndObserver mRecyclerViewScrollToEndObserver;
    private ITweetsRecyclerViewModelObservable mTweetsRecyclerViewModelObservable;

    private boolean mIsLastPageLoaded;
    private List<TweetModel> mTweetsData;

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
    public  void onLoadDataFailed()
    {
        mRecyclerViewAdapter.setLoadingMoreItem(false);
        mRecyclerViewScrollToEndObserver.setLoadingData(mIsLastPageLoaded);
    }

    @Override
    public void setRecyclerViewData(List<TweetModel> data, boolean isLastPage)
    {
        updateTweetsListner(data);
        mIsLastPageLoaded = isLastPage;
        mRecyclerViewAdapter.setLoadingMoreItem(false);
        mRecyclerViewAdapter.updateData(data);
        mRecyclerViewScrollToEndObserver.setLoadingData(mIsLastPageLoaded);
    }

    private void updateTweetsListner(List<TweetModel> data)
    {
        if(mTweetsData!=null)
        {
            for(int lop=0; lop<mTweetsData.size(); lop++)
            {
                mTweetsData.get(lop).setOnTweetModelSpannableClicked(null);
            }
        }
        mTweetsData = data;
        for(int lop=0; lop<mTweetsData.size(); lop++)
        {
            mTweetsData.get(lop).setOnTweetModelSpannableClicked(this);
        }
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

    @Override
    public void onTweetModelSpannableClicked(String hashtag)
    {
        mTweetsRecyclerViewModelObservable.notifyLoadTweets(hashtag);
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
