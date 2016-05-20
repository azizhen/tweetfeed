package co.za.hendricks.filemanagement;

import co.za.hendricks.dto.Tweet;
import co.za.hendricks.dto.TwitterUser;
import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by aziz on 2016/05/18.
 */
public class TwitterFileReader {

    private final File userFile;
    private final File tweetFile;

    public TwitterFileReader(String userFilePath, String tweetFilePath) {
        checkArgument(userFilePath != null, "Incorrect user.txt path");
        checkArgument(userFilePath.endsWith("user.txt"), "File path must contain user.txt");
        checkArgument(tweetFilePath != null, "Incorrect tweet.txt path");
        checkArgument(tweetFilePath.endsWith("tweet.txt"), "File path must contain tweet.txt");

        this.userFile = new File(userFilePath);
        this.tweetFile = new File(tweetFilePath);
    }

    public TwitterFileReader(File userFile, File tweetFile) {
        this.userFile = userFile;
        this.tweetFile = tweetFile;
    }

    public List<TwitterUser> getTwitterUsers() throws IOException {

        List <TwitterUser> twitterUsers = new ArrayList<TwitterUser>();
        List <String> result = Files.readLines(userFile, Charsets.US_ASCII);

        for(String user : result){

            String [] content = user.split("follows");
            String username = content[0].trim();
            String followers = content[1];
            if(doesUserExist(twitterUsers, username)){
                addFollowersToExistingUser(twitterUsers, username, followers);
            }else{
                TwitterUser twitterUser = createUserFromStringArray(username, followers);
                twitterUsers.add(twitterUser);
            }
        }

        addUsersNotFollowing(twitterUsers, result);


        return twitterUsers;

    }

    /**
     * Finds people being Followed in the list that have not yet been added as Twitter Users and adds them to list
     * @param twitterUsers
     * @param result
     * @return
     */
    private void addUsersNotFollowing(List <TwitterUser> twitterUsers, List<String> result) throws IOException {
        for(String userString : result){
            String [] content = userString.split("(follows|,)");
            for(String username : content){
                if(!doesUserExist(twitterUsers, username.trim())){
                    TwitterUser twitterUser = new TwitterUser();
                    twitterUser.setUserName(username.trim());
                    twitterUser.setTweets(new ArrayList<Tweet>());
                    twitterUsers.add(twitterUser);
                }
            }
        }
    }

    private boolean doesUserExist(List<TwitterUser> twitterUsers, String username) {
        boolean doesExist =  false;
        for(TwitterUser twitterUser : twitterUsers){
            if(twitterUser.getUserName().equalsIgnoreCase(username)){
                return true;
            }
        }

        return doesExist;

    }

    private void addFollowersToExistingUser(List<TwitterUser> twitterUsers, String username, String followers) {
        for(TwitterUser twitterUser : twitterUsers){
            if(twitterUser.getUserName().equalsIgnoreCase(username)){
                twitterUser.getFollowing().addAll(getFollowingUsers(followers));
            }
        }
    }

    private TwitterUser createUserFromStringArray(String username, String followers) throws IOException {
        TwitterUser twitterUser = new TwitterUser();
        twitterUser.setUserName(username);
        twitterUser.setFollowing(getFollowingUsers(followers));
        twitterUser.setTweets(retrieveTweets(twitterUser.getUserName(), twitterUser.getFollowing()));
        return twitterUser;
    }

    /**
     * Given a username and a list of users being followed, filter tweets applicable
     * @param userName
     * @param following - the list of users that this user is following
     * @return
     */
    private List<Tweet> retrieveTweets(String userName, HashSet<String> following) throws IOException {
        List <Tweet> tweets = getTweets();
        List <Tweet> filteredTweets = new ArrayList<Tweet>();

        for(Tweet tweet : tweets){

            String targetUsername = tweet.getTargetUsername();
            if(targetUsername.equalsIgnoreCase(userName) || following.contains(targetUsername)){
                filteredTweets.add(tweet);
            }
        }
        return filteredTweets;
    }

    private HashSet<String> getFollowingUsers(String followingUsers) {
        String  [] content = followingUsers.split(",");
        HashSet<String> followingSet = new HashSet<String>();
        for(String users : content){
            followingSet.add(users.trim());
        }

        return followingSet;

    }

    protected List<Tweet> getTweets() throws IOException {

        List <Tweet> tweets = new ArrayList<Tweet>();

        List<String> result = Files.readLines(tweetFile, Charsets.US_ASCII);

        for(String tweetLine : result){

            String [] content = tweetLine.split(">");

            if(isValidTweetString(content)){
                throw new IllegalArgumentException("Invalid Tweet String found");
            } else{
                tweets.add(createTweetFromStringArray(content));
            }
        }
        return tweets;
    }

    private Tweet createTweetFromStringArray(String[] content) {
        return new Tweet(content[0], content[1]);
    }

    private boolean isValidTweetString(String[] content) {
        return content.length > 2;
    }

    private boolean isAscii(File file) throws IOException {

        String result = Files.toString(file, Charsets.US_ASCII);
        return CharMatcher.ASCII.matchesAllOf(result);
    }

    public String getUserFileContents() throws IOException {
        return getStringContentsFromFile(userFile);
    }

    public String getTwitterFileContents() throws IOException {
        return getStringContentsFromFile(tweetFile);
    }

    private String getStringContentsFromFile(File file) throws IOException {
        return Files.toString(file, Charsets.US_ASCII);
    }

    public void isValid() throws IOException {

        if(!userFile.exists()){
            throw new IllegalArgumentException("User File does not exist");
        }

        if(!tweetFile.exists()){
            throw new IllegalArgumentException("Tweet File does not exist");
        }

        if(!isAscii(userFile)){
            throw new IllegalArgumentException("User File contents is not ASCII");

        }

        if(!isAscii(tweetFile)){
            throw new IllegalArgumentException("Tweet File contents is not ASCII");
        }

    }
}
