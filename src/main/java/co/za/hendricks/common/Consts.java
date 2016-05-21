package co.za.hendricks.common;

/**
 * Collected constants of general utility.
 *
 * All members of this class are immutable.
 *
 * @author  Aziz Hendricks
 * @version 1.0
 * @since   2016-5-20
 */

public final class Consts {

        public static final int PARAMATER_LOCATION_ONE = 0;
        public static final int PARAMATER_LOCATION_TWO= 1;
        public static final int MAX_PARAMETERS_ALLOWED = 2;

        public static final String TWEET_REGEX_FORMAT = "\t@%s: %s";
        public static final String USER_REGEX_FORMAT = "("+Consts.FOLLOWS+"|,)";
        public static final String TWEET_FILE_NAME = "tweet.txt";
        public static final String USER_FILE_NAME = "user.txt";
        public static final String FOLLOWS = "follows";
        public static final String USER_DELIMETER = ",";
        public static final String TWEET_DELIMETER = ">";


    /**
     * Prevent the caller from instantiating an instance of the class
     */
    private Consts(){
            throw new AssertionError();
        }

}
