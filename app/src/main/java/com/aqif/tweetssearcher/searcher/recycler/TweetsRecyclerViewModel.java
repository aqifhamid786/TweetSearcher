package com.aqif.tweetssearcher.searcher.recycler;

import android.support.v7.widget.RecyclerView;

import com.aqif.tweetssearcher.searcher.recycler.adapter.TweetsRecyclerAdapter;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/7/17.
 */

public class TweetsRecyclerViewModel
{

    private RecyclerView mRecyclerView;
    private TweetsRecyclerAdapter mRecyclerViewAdapter;


    public TweetsRecyclerViewModel(RecyclerView recyclerView, TweetsRecyclerAdapter recyclerViewAdapter)
    {
        mRecyclerView = recyclerView;
        mRecyclerViewAdapter = recyclerViewAdapter;
    }

}
