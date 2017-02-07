package com.aqif.tweetssearcher.searcher.recycler.viewmodel;

import android.support.v7.app.AppCompatActivity;

import com.aqif.tweetssearcher.searcher.base.BaseRecycleRViewAdapter;
import com.aqif.tweetssearcher.searcher.search.Tweet;;

import java.util.List;

/**
 * Created by aqifhamid on 2/9/16.
 */
public class TweetsRecyclerAdapter extends BaseRecycleRViewAdapter<Tweet>
{

    public TweetsRecyclerAdapter(AppCompatActivity context, int itemLayoutId, int itemVariableId)
    {
        super(context, itemLayoutId, itemVariableId);
        setHasStableIds(true);
    }

    public void updateData(List<Tweet> data)
    {
        mData = data;
        notifyDataSetChanged();
    }
}
