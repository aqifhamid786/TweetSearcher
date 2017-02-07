
package com.aqif.tweetssearcher.restapi.twitter.oauth.responsedao;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TwitterOAuthDAO {

    @SerializedName("token_type")
    @Expose
    private String tokenType;

    @SerializedName("access_token")
    @Expose
    private String accessToken;


    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
