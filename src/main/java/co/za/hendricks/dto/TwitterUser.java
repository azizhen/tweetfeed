package co.za.hendricks.dto;

import java.util.List;

/**
 * Created by aziz on 2016/05/18.
 */
public class TwitterUser {
    /*
     * Username of the twitter user
     */
    private String userName;

    /*
     * The list of users that this user is following
     */
    private List<String> following;

    /*
     * The list of Tweets that this user has made
     */
    private List<Tweet> tweets;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}
