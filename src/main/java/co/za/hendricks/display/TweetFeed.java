package co.za.hendricks.display;

import co.za.hendricks.dto.Tweet;
import co.za.hendricks.dto.TwitterUser;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by aziz on 2016/05/18.
 */
public class TweetFeed {

    List<TwitterUser> twitterUsers;

    public TweetFeed(List<TwitterUser> twitterUsers) {
        this.twitterUsers = twitterUsers;
    }


    // <tab>@user: <space>message.

    /**
     * Alan

     @Alan: If you have a procedure with 10 parameters, you probably missed some.

     @Alan: Random numbers should not be generated with a method chosen at random.
     */
    public void displayTweets() {

        sortAlphabetically(twitterUsers);

        for(TwitterUser twitterUser : twitterUsers){
            System.out.println(twitterUser.getUserName());
            printTweets(twitterUser);

        }
    }

    private void sortAlphabetically(List<TwitterUser> twitterUsers) {
        Collections.sort(twitterUsers);

    }

    private void printTweets(TwitterUser twitterUser) {
        for(Tweet tweet : twitterUser.getTweets()){
            System.out.println(String.format("\t@%s: %s", tweet.getTargetUsername(), tweet.getMessage()));

        }
    }
}
