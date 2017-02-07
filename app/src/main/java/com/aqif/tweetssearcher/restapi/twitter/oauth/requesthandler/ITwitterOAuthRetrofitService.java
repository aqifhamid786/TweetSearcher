package com.aqif.tweetssearcher.restapi.twitter.oauth.requesthandler;


import com.aqif.tweetssearcher.restapi.twitter.oauth.responsedao.TwitterOAuthDAO;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ITwitterOAuthRetrofitService
{
	@POST("/oauth2/token")
	//@Headers("Content-Type: application/x-www-form-urlencoded;charset=UTF-8")
	Call<TwitterOAuthDAO> getTwitterAccessToken(
			@Header("Authorization") String baseToken,
			@Body RequestBody body
	);

}
