package com.aqif.tweetssearcher.searcher.recycler.model;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;

/**
 * Created by aqifhamid on 2/5/17.
 */

public class TweetModel
{

    private String mName;
    private String mUserName;
    private String mTweetText;
    private int mReTweetCount;
    private int mFavouritiesCount;
    private IOnTweetModelSpannableClicked mOnTweetModelSpannableClicked;


    public TweetModel(String name, String userName, String tweetText, int reTweetCount, int favouritiesCount)
    {
        this.mName = name;
        this.mUserName = userName;
        this.mTweetText = tweetText;
        this.mReTweetCount = reTweetCount;
        this.mFavouritiesCount = favouritiesCount;
    }

    public String getName()
    {
        return mName;
    }

    public String getUserName()
    {
        return mUserName;
    }

    public int getReTweetCount()
    {
        return mReTweetCount;
    }

    public int getFavouritiesCount()
    {
        return mFavouritiesCount;
    }

    public String getTweetText()
    {
        return mTweetText;
    }

/** Highlights and converts tags into clickable items. */
    public SpannableString getSpannableTweetText()
    {

        SpannableString spanableString = new SpannableString(mTweetText);


        int HASHTAG = 0;
        int MENTION = 1;
        int start = -1;
        int type = -1;


        for(int lop=0; lop<mTweetText.length(); lop++)
        {
            if(mTweetText.charAt(lop)=='#')
            {
                processHashtagOrMention(spanableString, type, HASHTAG, MENTION, start, lop);
                start = lop;
                type = HASHTAG;
            }
            else if(mTweetText.charAt(lop)=='@')
            {
                processHashtagOrMention(spanableString, type, HASHTAG, MENTION, start, lop);
                start = lop;
                type = MENTION;
            }
            else if(mTweetText.charAt(lop)==' ' || lop == mTweetText.length()-1)
            {
                int end = lop+1;
                if(mTweetText.charAt(lop)==' ')
                    end = lop;

                processHashtagOrMention(spanableString, type, HASHTAG, MENTION, start, end);

                type = -1;
                start = -1;
            }
        }

        return spanableString;

    }

    private void processHashtagOrMention(SpannableString spanableString, int type, int HASHTAG, int MENTION, int start, int end)
    {
        if(type==HASHTAG)
        {
            processHashtags(spanableString, start, end);
        }
        else if(type==MENTION)
        {
            processMention(spanableString, start, end);
        }
    }

    private void processHashtags(SpannableString spanableString, int start, int end)
    {
        final String subStr = mTweetText.substring(start+1, end);
        spanableString.setSpan(new ClickableSpan()
        {
            @Override
            public void onClick(View view)
            {
                mOnTweetModelSpannableClicked.onTweetModelSpannableClicked(subStr);
            }
        }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanableString.setSpan(new ForegroundColorSpan(Color.parseColor("#5baaf4")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private void processMention(SpannableString spanableString, int start, int end)
    {
        spanableString.setSpan(new ForegroundColorSpan(Color.parseColor("#5baaf4")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

/** listner */
    public void setOnTweetModelSpannableClicked(IOnTweetModelSpannableClicked onTweetModelSpannableClicked)
    {
        mOnTweetModelSpannableClicked = onTweetModelSpannableClicked;
    }

/** Click listner for spannable */

    public interface IOnTweetModelSpannableClicked
    {
        void onTweetModelSpannableClicked(String tag);
    }


}
