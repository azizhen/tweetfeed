package co.za.hendricks.dto;

/**
 * Data structure for holding the content of a Tweet
 *
 * @author  Aziz Hendricks
 * @version 1.0
 * @since   2016-5-20
 */
public class Tweet {

    /**
     * The user that the tweet is meant for eg. @user
     */
    private String targetUsername;

    /**
     * The content of the message
     */
    private String message;

    public Tweet(String targetUsername, String message) {
        this.targetUsername = targetUsername;
        this.message = message;
    }

    public String getTargetUsername() {
        return targetUsername;
    }

    public void setTargetUsername(String targetUsername) {
        this.targetUsername = targetUsername;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
