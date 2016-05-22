## Introduction

This project demonstrates using File IO to read a list of Twitter Users and Tweets and then Write the formatted content to System Console.
It demonstrates examples of using the following:

Development Practice          | Framework
----------------------------- | -------------
Test Driven Development       | Junit
Behaviour Driven Development  | Cucumber
Code Quality Analysis         | CheckStyle and FindBugs

## Installation

**To build the executable:**

Install Java 7
Install Maven

Navigate to the root folder of the project where pom.xml is located.

Execute the following command

_mvn clean install_

This will generate a JAR file called tweetfeed-1.0-jar-with-dependencies.jar in the target folder.

**To run the executable**

Navigate to the target folder within the root of the project folder.

Provide two text file paths as input user.txt and tweet.txt. 

_java -jar tweetfeed-1.0-jar-with-dependencies.jar <PATH>/user.txt <PATH>/tweet.txt_

eg. 

_java -jar tweetfeed-1.0-jar-with-dependencies.jar /users/aziz/Downloads/user.txt /users/aziz/Downloads/tweet.txt_

## Assumptions

The executable jar file will be built on a system that has Maven installed and internet access to download dependencies.

The system will be run via Command Line on an operating system that has Java 7 installed.

The user will provide 2 files always named user.txt and tweet.txt in the correct sequence.


## Tests

To run the test suite run the following:

mvn test

## Contributors

Aziz Hendricks
