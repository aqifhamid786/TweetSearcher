package com.aqif.tweetssearcher.restapi.twitter.oauth.basictoken;

import android.util.Base64;

import com.aqif.tweetssearcher.restapi.TwitterConstants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by aqifhamid on 2/4/17.
 */

public class TwitterBasicTokenEncoder implements ITwitterBasicTokenEncoder {

    private String mConsumerKey;
    private String mConsumerSecret;
    private String encodedString;

    public TwitterBasicTokenEncoder(String consumerKey, String consumerSecret)
    {
        mConsumerKey = consumerKey;
        mConsumerSecret = consumerSecret;
    }

    @Override
    public String encode()
    {
        if(encodedString==null)
        {
            try
            {
                String encodedConsumerKey    =  URLEncoder.encode(TwitterConstants.TwitterConsumerKey, "UTF-8");
                String encodedConsumerSecret =  URLEncoder.encode(TwitterConstants.TwitterConsumerSecret, "UTF-8");
                String stringToEnchode = encodedConsumerKey+":"+encodedConsumerSecret;
                encodedString = Base64.encodeToString(stringToEnchode.getBytes(), Base64.NO_WRAP);
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
        return encodedString;
    }
}
