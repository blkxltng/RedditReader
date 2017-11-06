package com.blkxltng.redditreader;

import com.blkxltng.redditreader.reddit.RedditJSON;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by firej on 10/13/2017.
 */

public interface RedditClient {

    @GET("/r/{subReddit}/.json")
    Call<RedditJSON> jsonForSubreddit(@Path("subReddit") String subreddit);

    @GET("/r/{subReddit}/.json?count={postNum}&after={postId}")
    Call<RedditJSON> jsonForSubredditPageNum(@Path("subReddit") String subReddit, @Path("postNum") String postNum, @Path("postId") String postId);
}
