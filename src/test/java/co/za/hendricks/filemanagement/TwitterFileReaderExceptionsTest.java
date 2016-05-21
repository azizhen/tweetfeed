package co.za.hendricks.filemanagement;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author  Aziz Hendricks
 * @version 1.0
 * @since   2016-5-20
 */

public class TwitterFileReaderExceptionsTest {

    File userFile;
    File tweetFile;
    TwitterFileReader twitterFileReader;
    ClassLoader classLoader;

    @Before
    public void setup() throws URISyntaxException {
        classLoader = getClass().getClassLoader();
        userFile = new File( classLoader.getResource( "user.txt" ).toURI() );
        tweetFile = new File( classLoader.getResource( "tweet.txt" ).toURI() );
        twitterFileReader = new TwitterFileReader(userFile, tweetFile);

    }

    @Test (expected = IllegalArgumentException.class)
    public void should_fail_when_both_files_are_null() throws IOException {

        userFile = null;
        tweetFile = null;
        TwitterFileReader twitterFileReader = new TwitterFileReader(userFile, tweetFile);
        twitterFileReader.isValid();
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_fail_when_user_file_is_null() throws IOException {

        userFile = null;
        TwitterFileReader twitterFileReader = new TwitterFileReader(userFile, tweetFile);
        twitterFileReader.isValid();
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_fail_when_tweet_file_is_null() throws IOException {

        tweetFile = null;
        TwitterFileReader twitterFileReader = new TwitterFileReader(userFile, tweetFile);
        twitterFileReader.isValid();
    }
}