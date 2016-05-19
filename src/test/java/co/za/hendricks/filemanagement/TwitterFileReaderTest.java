package co.za.hendricks.filemanagement;

import co.za.hendricks.dto.Tweet;
import com.google.common.base.CharMatcher;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by aziz on 2016/05/18.
 */
public class TwitterFileReaderTest {

    File userFile;
    File tweetFile;
    TwitterFileReader twitterFileReader;

    @Before
    public void setup() throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        userFile = new File( classLoader.getResource( "user.txt" ).toURI() );
        tweetFile = new File( classLoader.getResource( "tweet.txt" ).toURI() );
        twitterFileReader = new TwitterFileReader(userFile, tweetFile);

    }

    @Test
    public void should_validate_files_succesfully() throws IOException, URISyntaxException {

        TwitterFileReader twitterFileReader = new TwitterFileReader(userFile, tweetFile);
        twitterFileReader.isValid();
        assertTrue(true);
    }

    @Test
    public void should_read_correct_user_contents() throws IOException {
        String userFileContents = twitterFileReader.getUserFileContents();
        String expectedResult =  "Ward follows Alan\n" +
                                 "Alan follows Martin\n" +
                                 "Ward follows Martin, Alan";

        //Remove whitespaces to focus on character compare
        assertEquals(CharMatcher.BREAKING_WHITESPACE.removeFrom(expectedResult),
                     CharMatcher.BREAKING_WHITESPACE.removeFrom(userFileContents));

    }

    @Test
    public void should_read_correct_tweet_contents() throws IOException {
        TwitterFileReader twitterFileReader = new TwitterFileReader(userFile, tweetFile);
        String userFileContents = twitterFileReader.getTwitterFileContents();
        String expectedResult =  "Alan> If you have a procedure with 10 parameters, you probably missed some.\n" +
                "Ward> There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors.\n" +
                "Alan> Random numbers should not be generated with a method chosen at random.";

        //Remove whitespaces to focus on character compare
        assertEquals(CharMatcher.BREAKING_WHITESPACE.removeFrom(expectedResult),
                CharMatcher.BREAKING_WHITESPACE.removeFrom(userFileContents));

    }

    @Test
    public void should_return_3_tweets() throws IOException {
        List<Tweet> tweetList = twitterFileReader.getTweets();
        assertEquals(3, tweetList.size());
    }

    @Test
    public void should_return_correct_tweet_contents() throws IOException {
        List<Tweet> tweetList = twitterFileReader.getTweets();

        assertEquals("If you have a procedure with 10 parameters, you probably missed some.", tweetList.get(0).getMessage().trim());
        assertEquals("Alan", tweetList.get(0).getTargetUsername().trim());

        assertEquals("There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors.", tweetList.get(1).getMessage().trim());
        assertEquals("Ward", tweetList.get(1).getTargetUsername().trim());

        assertEquals("Random numbers should not be generated with a method chosen at random.", tweetList.get(2).getMessage().trim());
        assertEquals("Alan", tweetList.get(2).getTargetUsername().trim());

    }



}