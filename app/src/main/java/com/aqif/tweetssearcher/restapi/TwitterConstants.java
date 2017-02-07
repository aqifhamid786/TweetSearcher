package com.aqif.tweetssearcher.restapi;

public class TwitterConstants
{


	public static final String TwitterConsumerKey = "PcS67oOjxVokrrvvLpP4v45ES";
	public static final String TwitterConsumerSecret = "YQzrhCJJfGPhCkJZ3BvD6VKycBNptDTugtarVS55vsEyxIZ9kR";

	public static final String TwitterBaseURL = "https://api.twitter.com/";

	public static final String TwitterOAuthBody = "grant_type=client_credentials";
	public static final String TwitterOAuthBodyContentType = "application/x-www-form-urlencoded;charset=UTF-8";
	public static final String TwiiterOAuthFailMessage = "Failed to authenticate with Twitter.";
	public static final int TweetsPerRequestCount = 20;

}