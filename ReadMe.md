# A test project to test UI & API build with the following tools:

#### Selenium

#### Rest Assured

#### TestNG

#### Cucumber-JVM

## UI:

To test the https://wwww.mailtravel.co.uk

To run the tests:

```
browser=chrome mvn clean test -Dtest="uk.onefamily.MailTravelTest" 
```

browser is the env var exposed and other browsers can be supplied as values This is configured
on ```BrowserSetUp.java```

The TESTNG Test file is at uk.onefamily.MailTravelTest
The test does the following:
1. Open mailtravel.co.uk
2. Verifies the title
3. Search for tour packages in India
4. Selects  “India 11Days - Classic Escorted Tours £1749”
    The price has been updated and now it is £1849
5. Asserts days in itinerary, price and telephone number
6. Selects a departure date and clicks on Book Online
7. Selects double room for 2 adults
8. Fill passenger details and continue
9. Verify the payment amount as per the accommodation selected for 2 adults

## API:

TestNG Tests for the Employee API are at EmployeeAPITest.java The URI rejects the request if too many requests are sent
in a short time. Have tested the tests in parts.
The API: https://dummy.restapiexample.com/api/v1/employees is rate limited.
One cannot make even 2 quick calls and it comes back with 429- Too many requests.

####Future Enhancements:
#### Cucumber Scenario can be made better.
#### The E2E scenario that has been added can be broken down into many scenarios


