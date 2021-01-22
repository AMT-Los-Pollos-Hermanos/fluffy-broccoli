Feature: Event can be triggered

  Background:
    Given an application named "test"
    And the client receives an API-KEY
    And the client posts /badges
    And the client posts a badge rule
    And the client posts a point scale called "PointScale1"

  Scenario: the client created an event
    When the client posts an event
    Then the client receives status code of 200
