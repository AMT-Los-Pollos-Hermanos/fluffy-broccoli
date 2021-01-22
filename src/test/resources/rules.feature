Feature: Rules can be retrieved

  Background:
    Given an application named "test"
    And the client receives an API-KEY

  Scenario: the client creates a badge-scale rule
    When the client posts a badge-scale rule
    Then the client receives status code of 201
    When the client get "/rules/1" with API-KEY
    Then the client receives status code of 200
    And the client receives the correct payload

  Scenario: the client creates a badge rule
    When the client posts a badge rule
    Then the client receives status code of 201
    When the client get "/rules/1" with API-KEY
    Then the client receives status code of 200
    And the client receives the correct payload

  Scenario: the client creates a scale rule
    When the client posts a scale rule
    Then the client receives status code of 201
    When the client get "/rules/1" with API-KEY
    Then the client receives status code of 200
    And the client receives the correct payload
