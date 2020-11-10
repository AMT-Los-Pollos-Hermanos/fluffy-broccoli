Feature: Badges can be retrived
  Scenario: client makes call to GET /badges
    When the client calls /badges
    Then the client receives status code of 200
    And the client receives an array of badges
