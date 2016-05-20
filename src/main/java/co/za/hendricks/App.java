package co.za.hendricks;


import co.za.hendricks.display.TweetFeed;
import co.za.hendricks.dto.TwitterUser;
import co.za.hendricks.filemanagement.TwitterFileReader;

import java.io.IOException;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws IOException {

//        String userfilePath = "/users/aziz/Downloads/user.txt";
//        String tweetfilePath = "/users/aziz/Downloads/tweet.txt";

        String userfilePath = args[0] ;
        String tweetfilePath = args[1];


        TwitterFileReader twitterFileReader = new TwitterFileReader(userfilePath, tweetfilePath);
        List<TwitterUser> twitterUsers = twitterFileReader.getTwitterUsers();

        TweetFeed tweetFeed = new TweetFeed(twitterUsers);
        tweetFeed.displayTweets();
    }
}
