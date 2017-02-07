package com.aqif.tweetssearcher.searcher.search;

import android.content.Context;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;

/**
 * Created by aqifhamid on 2/6/17.
 */

public class TweetsSearchView extends SearchView
{
    public TweetsSearchView(Context context)
    {
        super(context);
        init();
    }

    public TweetsSearchView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public TweetsSearchView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init()
    {
        setQueryHint("Search #HashTag");
    }


}
