package co.za.hendricks;


import co.za.hendricks.display.TweetFeed;
import co.za.hendricks.dto.TwitterUser;
import co.za.hendricks.filemanagement.TwitterFileReader;

import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class App
{
    public static void main( String[] args ) throws IOException {

        checkArgument(args.length == 2, "Two arguments must be provided eg. \\path\\user.txt \\path\\tweet.txt");

        String userfilePath = args[0] ;
        String tweetfilePath = args[1];

        TwitterFileReader twitterFileReader = new TwitterFileReader(userfilePath, tweetfilePath);
        List<TwitterUser> twitterUsers = twitterFileReader.getTwitterUsers();

        TweetFeed tweetFeed = new TweetFeed(twitterUsers);
        tweetFeed.displayTweets();
    }
}
