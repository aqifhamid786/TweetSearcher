package com.aqif.tweetssearcher.searcher.recycler.adapter;

import android.support.v7.app.AppCompatActivity;

import com.aqif.tweetssearcher.searcher.base.BaseRecycleRViewAdapter;
import com.aqif.tweetssearcher.searcher.search.Tweet;

/**
 * Created by aqifhamid on 2/9/16.
 */
public class TweetsRecyclerAdapter extends BaseRecycleRViewAdapter<Tweet>
{

    public TweetsRecyclerAdapter(AppCompatActivity context, int itemLayoutId, int itemVariableId)
    {
        super(context, itemLayoutId, itemVariableId);

        setHasStableIds(true);

        for(int lop=0; lop<25; lop++)
        {
            Tweet tweet = new Tweet("Aqif Hamid", "@aqif", "This is my first ever tweet. Who will Fav and Retweet it? :) ", 23, 54);
            mData.add(tweet);
        }
    }


}
