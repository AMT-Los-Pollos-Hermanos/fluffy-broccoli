Feature: Point Scales can be retrieved

  Background:
    Given an application named "test"
    And the client receives an API-KEY

  Scenario: the client get /pointscales and gets an empty array
    When the client get "/pointscales" with API-KEY
    Then the client receives status code of 200
    And the client receives an empty array

  Scenario: the client creates a point scale
    When the client posts a point scale
    Then the client receives status code of 201
    And the client receives the correct payload

