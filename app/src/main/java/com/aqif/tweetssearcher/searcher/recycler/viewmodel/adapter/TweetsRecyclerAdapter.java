package com.aqif.tweetssearcher.searcher.recycler.viewmodel.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.aqif.tweetssearcher.R;
import com.aqif.tweetssearcher.BR;
import com.aqif.tweetssearcher.searcher.recycler.model.TweetModel;;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aqifhamid on 2/9/16.
 */
public class TweetsRecyclerAdapter extends RecyclerView.Adapter<TweetViewHolder>
{

    private static final int TWEET_CARD = 0;
    private static final int LOADING_CARD = 1;

    private List<TweetModel> mTweetModels;
    private AppCompatActivity mContext;

    private int mItemLayoutId;
    private int mItemVariableId;

    private boolean mLoadingMoreItem;

    public TweetsRecyclerAdapter(AppCompatActivity context)
    {
        mContext = context;
        mTweetModels = new ArrayList<>();
    }

    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        ViewDataBinding viewDataBinding = null;
        switch(viewType)
        {
            case TWEET_CARD:
                viewDataBinding = DataBindingUtil.inflate(mContext.getLayoutInflater(), R.layout.tweet_card_view, parent, false);
                break;
            case LOADING_CARD:
                viewDataBinding = DataBindingUtil.inflate(mContext.getLayoutInflater(), R.layout.loading_view_card, parent, false);
                break;
        }

        return new TweetViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(TweetViewHolder holder, int position)
    {
        if(position< mTweetModels.size())
            holder.getViewDataBinding().setVariable(BR.tweetModel, mTweetModels.get(position));
    }

    @Override
    public int getItemViewType(int position)
    {
        return position == mTweetModels.size() ? LOADING_CARD : TWEET_CARD;
    }

    @Override
    public int getItemCount()
    {
        return mLoadingMoreItem ? mTweetModels.size()+1 : mTweetModels.size();
    }


    @Override
    public long getItemId(int position)
    {
        return position;
    }

    public void setLoadingMoreItem(boolean loadingMoreItem)
    {
        if(mLoadingMoreItem == loadingMoreItem)
            return;

        mLoadingMoreItem = loadingMoreItem;
        if(mLoadingMoreItem)
            notifyItemInserted(getItemCount());
        else
            notifyItemRemoved(getItemCount()+1);
    }

    public void updateData(List<TweetModel> tweetModels)
    {
        while(mTweetModels.size()>tweetModels.size())
        {
            int removeIndex = mTweetModels.size()-1;
            notifyItemRemoved(removeIndex);
            mTweetModels.remove(removeIndex);
        }

        for(int lop=0; lop<mTweetModels.size() && lop<tweetModels.size(); lop++)
        {
            mTweetModels.set(lop, tweetModels.get(lop));
            notifyItemChanged(lop);
        }

        while(mTweetModels.size()<tweetModels.size())
        {
            mTweetModels.add(tweetModels.get(mTweetModels.size()));
            notifyItemInserted(mTweetModels.size()-1);
        }

    }
}
