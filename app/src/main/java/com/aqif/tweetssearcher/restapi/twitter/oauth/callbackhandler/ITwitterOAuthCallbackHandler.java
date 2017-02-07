package com.aqif.tweetssearcher.restapi.twitter.oauth.callbackhandler;

import com.aqif.tweetssearcher.restapi.twitter.oauth.responsedao.TwitterOAuthDAO;

import retrofit2.Call;
import retrofit2.Callback;

public interface ITwitterOAuthCallbackHandler extends ITwitterOAuthCallbackObservable, Callback<TwitterOAuthDAO>
{
    Call<TwitterOAuthDAO> getOAuthCall();
    void setOAuthCall(Call<TwitterOAuthDAO> call);
}
