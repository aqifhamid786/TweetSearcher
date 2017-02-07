package com.aqif.tweetssearcher.searcher.recycler.dagger;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.aqif.tweetssearcher.BR;
import com.aqif.tweetssearcher.R;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.ITweetsRecyclerViewModel;
import com.aqif.tweetssearcher.searcher.recycler.view.TweetsRecyclerView;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.TweetsRecyclerViewModel;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.TweetsRecyclerAdapter;

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
    ITweetsRecyclerViewModel getTweetsRecyclerViewModel()
    {
        mTweetsRecyclerView.setLayoutManager(new LinearLayoutManager(mAppCompatActivity));
        return new TweetsRecyclerViewModel(mTweetsRecyclerView, new TweetsRecyclerAdapter(mAppCompatActivity, R.layout.tweet_card_view, BR.tweet));
    }

}
