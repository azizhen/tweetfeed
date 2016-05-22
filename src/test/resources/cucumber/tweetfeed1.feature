Feature: Display Twitter Feed
  As a user
  I want to be able to process text files that contain a twitter feed
  So that I can display a Twitter Feed

  Scenario: Display Twitter Feed based on two Files as input

    Given user file named user.txt
    And   tweet file named tweet.txt
    When  I invoke a  program with user.txt and tweet.txt as arguments
    Then  then 3 users must be displayed
    And   5 tweets in total