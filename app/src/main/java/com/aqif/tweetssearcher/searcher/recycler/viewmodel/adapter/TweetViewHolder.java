package com.aqif.tweetssearcher.searcher.recycler.viewmodel.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by aqifhamid on 2/14/16.
 */
public class TweetViewHolder extends RecyclerView.ViewHolder
{

    private ViewDataBinding mDataBinding;

    public TweetViewHolder(ViewDataBinding dataBindind)
    {
        super(dataBindind.getRoot());
        mDataBinding = dataBindind;
        mDataBinding.executePendingBindings();
    }

    public ViewDataBinding getViewDataBinding()
    {
        return mDataBinding;
    }

}
