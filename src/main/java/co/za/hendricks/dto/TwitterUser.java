package co.za.hendricks.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by aziz on 2016/05/18.
 */
public class TwitterUser implements Comparable <TwitterUser>{
    /*
     * Username of the twitter user
     */
    private String userName;

    //
    //* The list of unqique users that this user is following
    //
    private HashSet <String> following;

    /*
     * The list of Tweets that this user has made
     */
    private List<Tweet> tweets;

    public TwitterUser() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public HashSet<String> getFollowing() {
        return following;
    }

    public void setFollowing(HashSet<String> following) {
        this.following = following;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }


    public int compareTo(TwitterUser o) {

        int result = this.getUserName().compareTo(o.getUserName());
        //ascending order
        return result;
    }
}
