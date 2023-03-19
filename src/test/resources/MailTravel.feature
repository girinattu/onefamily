Feature:
  Test mail travel tour packages for India

  Scenario: Test Tour package - India 11Days - Classic Escorted Tours £1849
    Given a customer is on the mail travel webpage
    When the customer searches for India
    And the customer selects 11 Days - Classic Escorted Tours
    Then the customer is able to select 2 passengers
    And the price is £1849 per passenger and the customer can fill the passenger details