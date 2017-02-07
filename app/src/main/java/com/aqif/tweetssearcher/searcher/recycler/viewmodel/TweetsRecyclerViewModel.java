package com.aqif.tweetssearcher.searcher.recycler.viewmodel;

import android.support.v7.widget.RecyclerView;

import com.aqif.tweetssearcher.searcher.search.Tweet;
import com.aqif.tweetssearcher.searcher.search.viewmodel.ITweetsSearchViewModel;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/7/17.
 */

public class TweetsRecyclerViewModel implements ITweetsRecyclerViewModel
{

    private RecyclerView mRecyclerView;
    private TweetsRecyclerAdapter mRecyclerViewAdapter;


    @Inject
    public TweetsRecyclerViewModel(RecyclerView recyclerView, TweetsRecyclerAdapter recyclerViewAdapter)
    {
        mRecyclerView = recyclerView;
        mRecyclerViewAdapter = recyclerViewAdapter;
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    public void setRecyclerViewData(List<Tweet> data)
    {
        mRecyclerViewAdapter.updateData(data);
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
