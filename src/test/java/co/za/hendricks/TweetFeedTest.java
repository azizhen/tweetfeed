package co.za.hendricks;

import co.za.hendricks.common.Consts;
import co.za.hendricks.display.TweetFeed;
import co.za.hendricks.dto.TwitterUser;
import co.za.hendricks.filemanagement.TwitterFileReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author  Aziz Hendricks
 * @version 1.0
 * @since   2016-5-20
 */

public class TweetFeedTest {

    private File userFile;
    private File tweetFile;

    @Before
    public void setup() throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        userFile = new File( classLoader.getResource( Consts.USER_FILE_NAME ).toURI() );
        tweetFile = new File( classLoader.getResource(Consts.TWEET_FILE_NAME).toURI() );
    }

    @Test
    public void should_return_twitter_feed() throws URISyntaxException, IOException {

        TwitterFileReader twitterFileReader = new TwitterFileReader(userFile, tweetFile);
        List <TwitterUser> twitterUsers = twitterFileReader.getTwitterUsers();

        TweetFeed tweetFeed = new TweetFeed(twitterUsers);
        tweetFeed.displayTweets();

    }

    @Test
    public void should_maintain_insertion_order(){
        ArrayList <String> inputList = new ArrayList();
        inputList.add("1");
        inputList.add("2");
        inputList.add("3");

        Assert.assertEquals("1", inputList.get(0));
        Assert.assertEquals("2", inputList.get(1));
        Assert.assertEquals("3", inputList.get(2));

    }
}
