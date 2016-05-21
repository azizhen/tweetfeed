package co.za.hendricks.filemanagement;

import co.za.hendricks.common.Consts;
import co.za.hendricks.dto.Tweet;
import co.za.hendricks.dto.TwitterUser;
import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.sun.tools.internal.jxc.ap.Const;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Reads in the contents of a Text File and converts into a {@link TwitterUser} list
 *
 * @author  Aziz Hendricks
 * @version 1.0
 * @since   2016-5-20
 */

public class TwitterFileReader {

    private final File userFile;
    private final File tweetFile;

    /**
     * Construct using File Paths
     * @param userFilePath
     * @param tweetFilePath
     */
    public TwitterFileReader(String userFilePath, String tweetFilePath) {
        validateArguments(userFilePath, tweetFilePath);
        this.userFile = new File(userFilePath);
        this.tweetFile = new File(tweetFilePath);
    }

    /**
     * Construct using Files
     * @param userFile
     * @param tweetFile
     */
    public TwitterFileReader(File userFile, File tweetFile) {
        this.userFile = userFile;
        this.tweetFile = tweetFile;
    }

    /**
     * Reads in the content of the file and converts it into a List of {@link TwitterUser}
     *
     * @return List of {@link TwitterUser}
     * @throws IOException
     */
    public List<TwitterUser> getTwitterUsers() throws IOException {

        List <TwitterUser> twitterUsers = new ArrayList<TwitterUser>();
        List <String> result = Files.readLines(userFile, Charsets.US_ASCII);

        for(String user : result){

            String [] content = user.split(Consts.FOLLOWS);
            String username = content[Consts.PARAMATER_LOCATION_ONE].trim();
            String followers = content[Consts.PARAMATER_LOCATION_TWO];
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
            String [] content = userString.split(Consts.USER_REGEX_FORMAT);
            findUsersNotFollowing(twitterUsers, content);
        }
    }

    /**
     * Finds Users in String Content that dont exist in {@link TwitterUser} list
     * @param twitterUsers
     * @param content
     */
    private void findUsersNotFollowing(List<TwitterUser> twitterUsers, String[] content) {
        for(String username : content){
            if(!doesUserExist(twitterUsers, username.trim())){
                TwitterUser twitterUser = new TwitterUser();
                twitterUser.setUserName(username.trim());
                twitterUser.setTweets(new ArrayList<Tweet>());

                twitterUsers.add(twitterUser);
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
        String  [] content = followingUsers.split(Consts.USER_DELIMETER);
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

            String [] content = tweetLine.split(Consts.TWEET_DELIMETER);

            if(isValidTweetString(content)){
                throw new IllegalArgumentException("Invalid Tweet String found");
            } else{
                tweets.add(createTweetFromStringArray(content));
            }
        }
        return tweets;
    }

    private Tweet createTweetFromStringArray(String[] content) {
        return new Tweet(content[Consts.PARAMATER_LOCATION_ONE], content[Consts.PARAMATER_LOCATION_TWO]);
    }

    private boolean isValidTweetString(String[] content) {
        return content.length > Consts.MAX_PARAMETERS_ALLOWED;
    }

    private boolean isAscii(String result) throws IOException {

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

        checkArgument(userFile != null, "Must provide valid File object. Object cannot be null.");
        checkArgument(tweetFile != null, "Must provide valid File object. Object cannot be null.");

        if(!userFile.exists()){
            throw new IllegalArgumentException("User File does not exist");
        }

        if(!tweetFile.exists()){
            throw new IllegalArgumentException("Tweet File does not exist");
        }

        String userContent = Files.toString(userFile, Charsets.US_ASCII);
        String tweetContent = Files.toString(tweetFile, Charsets.US_ASCII);

        if(!isAscii(userContent)){
            throw new IllegalArgumentException("User File contents is not ASCII");
        }

        if(!isAscii(tweetContent)){
            throw new IllegalArgumentException("Tweet File contents is not ASCII");
        }
    }

    private void validateArguments(String userFilePath, String tweetFilePath) {
        checkArgument(userFilePath != null, "Incorrect " + Consts.USER_FILE_NAME +" path");
        checkArgument(userFilePath.endsWith(Consts.USER_FILE_NAME), "File path must contain "+Consts.USER_FILE_NAME);
        checkArgument(tweetFilePath != null, "Incorrect "+Consts.TWEET_FILE_NAME+" path");
        checkArgument(tweetFilePath.endsWith(Consts.TWEET_FILE_NAME), "File path must contain "+Consts.TWEET_FILE_NAME);
    }
}
