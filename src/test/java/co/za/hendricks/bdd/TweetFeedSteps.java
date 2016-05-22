package co.za.hendricks.bdd;

import co.za.hendricks.dto.TwitterUser;
import co.za.hendricks.filemanagement.TwitterFileReaderTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author  Aziz Hendricks
 * @version 1.0
 * @since   2016-5-20
 */

public class TweetFeedSteps extends TwitterFileReaderTest{

    List<TwitterUser> twitterUserList = new ArrayList();

    @Given("^user file named user\\.txt$")
    public void user_file_named_user_txt() throws Throwable {

        //This will setup with the input files user.txt and tweet.txt
        setup();
        userFile = new File( classLoader.getResource( "user.txt" ).toURI() );
    }

    @Given("^tweet file named tweet\\.txt$")
    public void tweet_file_named_tweet_txt() throws Throwable {
        tweetFile = new File( classLoader.getResource( "tweet.txt" ).toURI() );
    }

    @When("^I invoke a  program with user\\.txt and tweet\\.txt as arguments$")
    public void i_invoke_a_program_with_user_txt_and_tweet_txt_as_arguments() throws Throwable {
        twitterUserList = twitterFileReader.getTwitterUsers();
    }

    @Then("^then (\\d+) users must be displayed$")
    public void then_users_must_be_displayed(int numberOfUsers) throws Throwable {
        assertEquals(numberOfUsers, twitterUserList.size());
    }

    @Then("^(\\d+) tweets in total$")
    public void tweets_in_total(int numberOfTweets) throws Throwable {

        int countOfTweets = 0;
        for(TwitterUser twitterUser : twitterUserList){
            countOfTweets = countOfTweets + twitterUser.getTweets().size();
        }
        assertEquals(numberOfTweets, countOfTweets);

    }
}
