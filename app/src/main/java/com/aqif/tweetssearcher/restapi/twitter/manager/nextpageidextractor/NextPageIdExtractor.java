package com.aqif.tweetssearcher.restapi.twitter.manager.nextpageidextractor;

/**
 * Created by aqifhamid on 2/5/17.
 */

public class NextPageIdExtractor implements INextPageIdExtractor
{
    @Override
    public String extract(String from)
    {
        if(from==null || from.length()==0)
            return null;
        return from.split("max_id=")[1].split("&")[0];
    }
}
