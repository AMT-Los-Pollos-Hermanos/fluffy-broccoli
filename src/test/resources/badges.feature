Feature: Badges can be retrieved
  Scenario: the client makes call to GET /badges with no application
    When the client get "/badges"
    Then the client receives status code of 403


  Scenario: the client makes call to GET /badges with wrong API-KEY
    Given an application named "test"
    And the client receives an API-KEY
    When the client get "/badges" with wrong API-KEY
    Then the client receives status code of 403


  Scenario: the client makes call to GET /badges with existing application
    Given an application named "test"
    And the client receives an API-KEY
    When the client get "/badges" with API-KEY
    Then the client receives status code of 200
    And the client receives an empty array of badges


  #Scenario:
   # Given an application named "test"
   # And the client receives an API-KEY
   # When the client get "/badges" with API-KEY
   # Then the client receives status code of 200
