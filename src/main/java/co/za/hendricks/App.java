package co.za.hendricks;


import co.za.hendricks.common.Consts;
import co.za.hendricks.display.TweetFeed;
import co.za.hendricks.dto.TwitterUser;
import co.za.hendricks.filemanagement.TwitterFileReader;

import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * This is the main class used for execution via command line in order to read in
 * two file paths provided using {@link TwitterFileReader}  and then processes the twitter content and prints
 * the formatted content to console using {@link TweetFeed}
 *
 * @author  Aziz Hendricks
 * @version 1.0
 * @since   2016-5-20
 */
public class App
{
    public static void main( String[] args ) throws IOException {

        validateCommandLineArguments(args);

        String userfilePath = args[Consts.PARAMATER_LOCATION_ONE] ;
        String tweetfilePath = args[Consts.PARAMATER_LOCATION_TWO];

        TwitterFileReader twitterFileReader = new TwitterFileReader(userfilePath, tweetfilePath);
        List<TwitterUser> twitterUsers = twitterFileReader.getTwitterUsers();

        TweetFeed tweetFeed = new TweetFeed(twitterUsers);
        tweetFeed.displayTweets();
    }

    private static void validateCommandLineArguments(String[] args) {
        checkArgument(args.length == Consts.MAX_PARAMETERS_ALLOWED,
                                "Two arguments must be provided eg. \\path\\"
                                + Consts.USER_FILE_NAME + "\\path\\"
                                + Consts.USER_FILE_NAME);
    }
}
