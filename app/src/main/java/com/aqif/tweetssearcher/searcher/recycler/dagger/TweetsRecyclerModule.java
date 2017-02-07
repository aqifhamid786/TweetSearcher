package com.aqif.tweetssearcher.searcher.recycler.dagger;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.aqif.tweetssearcher.searcher.recycler.viewmodel.ITweetsRecyclerViewModel;
import com.aqif.tweetssearcher.searcher.recycler.view.TweetsRecyclerView;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.observer.RecyclerViewScrollToEndObserver;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.TweetsRecyclerViewModel;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.adapter.TweetsRecyclerAdapter;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.observer.ITweetsRecyclerViewModelObservable;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.observer.ITweetsRecyclerViewModelObserver;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.observer.TweetsRecyclerViewModelObservable;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aqifhamid on 2/6/17.
 */

@Module
public class TweetsRecyclerModule
{

    private AppCompatActivity mAppCompatActivity;
    private TweetsRecyclerView mTweetsRecyclerView;

    public TweetsRecyclerModule(AppCompatActivity appCompatActivity, TweetsRecyclerView tweetsRecyclerView)
    {
        mAppCompatActivity = appCompatActivity;
        mTweetsRecyclerView = tweetsRecyclerView;
    }

    @Provides
    ITweetsRecyclerViewModelObservable provideTweetsRecyclerViewModelObservable()
    {
        return new TweetsRecyclerViewModelObservable(new ArrayList<ITweetsRecyclerViewModelObserver>());
    }

    @Provides
    ITweetsRecyclerViewModel getTweetsRecyclerViewModel(ITweetsRecyclerViewModelObservable tweetsRecyclerViewModelObservable)
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mAppCompatActivity);
        mTweetsRecyclerView.setLayoutManager(linearLayoutManager);

        return new TweetsRecyclerViewModel(
                mTweetsRecyclerView,
                new TweetsRecyclerAdapter(mAppCompatActivity),
                tweetsRecyclerViewModelObservable,
                new RecyclerViewScrollToEndObserver(linearLayoutManager));
    }

}
