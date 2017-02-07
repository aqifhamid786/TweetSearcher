package com.aqif.tweetssearcher.restapi.twitter.searchtweets.requesthandler;


import com.aqif.tweetssearcher.restapi.twitter.searchtweets.responsedao.TweetsSearchDAO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface ITweetsSearchRetrofitService
{
	@GET("/1.1/search/tweets.json")
	Call<TweetsSearchDAO> searchTweets(
			@Header("Authorization") String bearerToken,
			@Query("q") String query,
			@Query("count") int count
	);

	@GET("/1.1/search/tweets.json")
	Call<TweetsSearchDAO> loadMoreTweets(
			@Header("Authorization") String bearerToken,
			@Query("q") String query,
			@Query("max_id") String maxId,
			@Query("count") int count
	);

}
