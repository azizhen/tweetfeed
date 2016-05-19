package co.za.hendricks;

import co.za.hendricks.display.TweetFeed;
import co.za.hendricks.dto.TwitterUser;
import co.za.hendricks.filemanagement.TwitterFileReader;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aziz on 2016/05/18.
 */
public class TweetFeedTest {

    @Ignore
    @Test
    public void should_return_twitter_feed() throws URISyntaxException, IOException {

        File userFile = new File( this.getClass().getResource( "user.txt" ).toURI() );
        File tweetFile = new File( this.getClass().getResource( "tweet.txt" ).toURI() );
        TwitterFileReader twitterFileReader = new TwitterFileReader(userFile, tweetFile);
        List <TwitterUser> twitterUsers = twitterFileReader.processFiles();

        TweetFeed tweetFeed = new TweetFeed(twitterUsers);
        tweetFeed.displayTweets();

    }

    @Test
    public void should_maintain_insertion_order(){
        List <String> inputList = new ArrayList();
        inputList.add("1");
        inputList.add("2");
        inputList.add("3");

        Assert.assertEquals("1", inputList.get(0));
        Assert.assertEquals("2", inputList.get(1));
        Assert.assertEquals("3", inputList.get(2));

    }
}
