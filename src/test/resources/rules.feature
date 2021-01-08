Feature: Rules can be retrieved

  Background:
    Given an application named "test"
    And the client receives an API-KEY
    And the client posts /badges 5 times


  Scenario: the client creates a rule
    When the client posts /rules
    Then the client receives status code of 200
    When the client get "/rules" with API-KEY
    Then the client receives status code of 200
    # Need to be changed or generalised
    And the client receives an empty array of badges

