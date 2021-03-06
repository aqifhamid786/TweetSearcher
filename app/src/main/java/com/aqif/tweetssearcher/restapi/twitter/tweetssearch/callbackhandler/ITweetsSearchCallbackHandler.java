package com.aqif.tweetssearcher.restapi.twitter.tweetssearch.callbackhandler;

import com.aqif.tweetssearcher.restapi.twitter.tweetssearch.responsedao.TweetsSearchDAO;

import retrofit2.Call;
import retrofit2.Callback;

public interface ITweetsSearchCallbackHandler extends ITweetsSearchCallbackObservable, Callback<TweetsSearchDAO>
{
    void setSearchCall(Call<TweetsSearchDAO> call);
    Call<TweetsSearchDAO> getSearchCall();
}
