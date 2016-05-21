package co.za.hendricks.filemanagement;

import co.za.hendricks.dto.Tweet;
import co.za.hendricks.dto.TwitterUser;
import com.google.common.base.CharMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.IllegalFormatException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by aziz on 2016/05/18.
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