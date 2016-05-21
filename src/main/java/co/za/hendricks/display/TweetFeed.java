package co.za.hendricks.display;

import co.za.hendricks.common.Consts;
import co.za.hendricks.display.intf.ConsoleWriter;
import co.za.hendricks.display.intf.OutputWriter;
import co.za.hendricks.dto.Tweet;
import co.za.hendricks.dto.TwitterUser;

import java.util.Collections;
import java.util.List;

/**
 * This class is responsible for displaying the twitter feed to the Console
 * using the provided list of {@link TwitterUser}
 *
 * @author  Aziz Hendricks
 * @version 1.0
 * @since   2016-5-20
 */

public class TweetFeed {

    List<TwitterUser> twitterUsers;

    public TweetFeed(List<TwitterUser> twitterUsers) {
        this.twitterUsers = twitterUsers;
    }

    /**
     * Displays Tweets contained in {@link TwitterUser} list in Alphabetically Ascending order
     *
     */
    public void displayTweets() {

        sortAlphabeticallyAscending(twitterUsers);

        for(TwitterUser twitterUser : twitterUsers){
            printTweets(twitterUser);
        }
    }

    private void sortAlphabeticallyAscending(List<TwitterUser> twitterUsers) {
        Collections.sort(twitterUsers);
    }

    private void printTweets(TwitterUser twitterUser) {
        OutputWriter outputWriter = new ConsoleWriter();

        outputWriter.write(twitterUser.getUserName());
        for(Tweet tweet : twitterUser.getTweets()){
            outputWriter.write(String.format(Consts.TWEET_REGEX_FORMAT, tweet.getTargetUsername(), tweet.getMessage()));
        }
    }
}
