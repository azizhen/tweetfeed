package co.za.hendricks.filemanagement;

import co.za.hendricks.dto.Tweet;
import co.za.hendricks.dto.TwitterUser;
import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aziz on 2016/05/18.
 */
public class TwitterFileReader {

    private File userFile;
    private File tweetFile;

    public TwitterFileReader(String userFilePath, String tweetFilePath) {
        this.userFile = new File(userFilePath);
        this.tweetFile = new File(tweetFilePath);

    }

    public TwitterFileReader(File userFile, File tweetFile) {
        this.userFile = userFile;
        this.tweetFile = tweetFile;
    }

    public List<TwitterUser> processFiles() throws IOException {

        //Retrieve all the tweets
        List <Tweet> tweets = getTweets();

        //Setup the list of users
       // TwitterUser

        return null;
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
