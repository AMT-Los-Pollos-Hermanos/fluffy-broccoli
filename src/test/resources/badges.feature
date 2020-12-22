Feature: Badges can be retrieved
  Scenario: the client makes call to GET /badges with no application
    When the client get "/badges"
    Then the client receives status code of 403


  Scenario: the client makes call to GET /badges with wrong API-KEY
    Given an application named "test"
    And the client receives an API-KEY
    When the client get "/badges" with wrong API-KEY
    Then the client receives status code of 403


  Scenario: the client makes call to GET /badges with existing application and no posted badge
    Given an application named "test"
    And the client receives an API-KEY
    When the client get "/badges" with API-KEY
    Then the client receives status code of 200
    And the client receives an empty array of badges

  Scenario: the client makes call to GET /badges with existing application
    Given an application named "test"
    And the client receives an API-KEY
    When the client posts /badges 5 times
    Then the client receives status code of 201
    When the client get "/badges" with API-KEY
    Then the client receives an array of 5 badges

  Scenario: the client makes call to GET /badges/1 with existing application
    Given an application named "test"
    And the client receives an API-KEY
    When the client posts /badges
    Then the client receives status code of 201
    When the client get "/badges/6" with API-KEY
    Then the client receives a badge



