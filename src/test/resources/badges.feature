Feature: Badges can be retrieved
  Scenario: the client makes call to GET /badges with no application
    When the client get "/badges"
    Then the client receives status code of 403


  Scenario: the client makes call to GET /badges with existing application
    Given an application named "test"
    When the client get "/badges"
    Then the client receives status code of 403