package co.za.hendricks.dto;

/**
 * Created by aziz on 2016/05/19.
 */
public class Tweet {

    /*
     * The user that the tweet is meant for eg. @user
     */
    private String targetUsername;

    /*
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
