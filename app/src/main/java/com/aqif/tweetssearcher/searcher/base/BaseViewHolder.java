package com.aqif.tweetssearcher.searcher.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by aqifhamid on 2/14/16.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder
{

    private ViewDataBinding mDataBinding;

    public BaseViewHolder(ViewDataBinding dataBindind)
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
