package com.aqif.tweetssearcher.searcher.recycler.model;

import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.responsedao.Hashtag;
import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.responsedao.UserMention;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    private List<Hashtag> hashtags = null;
    private List<UserMention> userMentions = null;

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

    public List<Hashtag> getHashtags()
    {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags)
    {
        this.hashtags = hashtags;
    }

    public List<UserMention> getUserMentions()
    {
        return userMentions;
    }

    public void setUserMentions(List<UserMention> userMentions)
    {
        this.userMentions = userMentions;
    }

    public String getTweetText()
    {
        return mTweetText;
    }

    // TODO: Add Factory for spannables.
    public SpannableString getSpannableTweetText()
    {

        SpannableString spanableString = new SpannableString(mTweetText);

        for(int lop=0; lop<userMentions.size(); lop++)
        {
            int startIndex = userMentions.get(lop).getIndices().get(0);
            int endIndex   = userMentions.get(lop).getIndices().get(1);
            spanableString.setSpan(new ForegroundColorSpan(Color.parseColor("#5baaf4")), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        }

        for(int lop=0; lop<hashtags.size(); lop++)
        {
            final Hashtag hashtag = hashtags.get(lop);
            int startIndex = hashtag.getIndices().get(0);
            int endIndex   = hashtag.getIndices().get(1);
            spanableString.setSpan(new ClickableSpan()
            {
                @Override
                public void onClick(View view)
                {
                    mOnTweetModelSpannableClicked.onTweetModelSpannableClicked(hashtag.getText());
                }
            }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanableString.setSpan(new ForegroundColorSpan(Color.parseColor("#5baaf4")), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return spanableString;

    }


    public void setOnTweetModelSpannableClicked(IOnTweetModelSpannableClicked onTweetModelSpannableClicked)
    {
        mOnTweetModelSpannableClicked = onTweetModelSpannableClicked;
    }

    /**** Click listner for spannable *****/

    public interface IOnTweetModelSpannableClicked
    {
        void onTweetModelSpannableClicked(String tag);
    }


}
