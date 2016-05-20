## Introduction

This project demonstrates using File IO to read a list of Twitter Users and Tweets

## Installation

To build the executable:

mvn clean install

This will generate a JAR file called tweetfeed-1.0-jar-with-dependencies.jar in the target folder.

To run the file:

Provide two text file paths as input user.txt and tweet.txt. 

Ensure Java 7 is installed.

eg. 

java -jar tweetfeed-1.0-jar-with-dependencies.jar /users/aziz/Downloads/user.txt /users/aziz/Downloads/tweet.txt


## Tests

To run the test suite run the following:

mvn test

## Contributors

Aziz Hendricks
