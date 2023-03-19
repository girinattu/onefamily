# A test project to test UI & API build with the following tools:

####Selenium
####Rest Assured
####TestNG
####Cucumber-JVM

##UI:
To test the https://wwww.mailtravel.co.uk

To run the tests:
```
browser=chrome mvn clean test -Dtest="uk.onefamily.MailTravelTest" 
```
browser is the env var exposed and other browsers can be supplied as values
This is configured on ```BrowserSetUp.java```

The TESTNG Test file is at uk.onefamily.MailTravelTest

##API:
TestNG Tests for the Employee API are at EmployeeAPITest.java
The URI rejects the request if too many requests are sent in a short time.
Have tested the tests in parts.
