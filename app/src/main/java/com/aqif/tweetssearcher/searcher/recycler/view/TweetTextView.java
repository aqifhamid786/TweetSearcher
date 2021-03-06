package com.aqif.tweetssearcher.searcher.recycler.view;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by aqifhamid on 2/7/17.
 */

public class TweetTextView extends TextView
{
    public TweetTextView(Context context)
    {
        super(context);
    }

    public TweetTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public TweetTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type)
    {
        super.setText(text, type);
        setMovementMethod(LinkMovementMethod.getInstance());
    }


}
