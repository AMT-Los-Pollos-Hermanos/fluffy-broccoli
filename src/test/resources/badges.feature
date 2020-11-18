Feature: Badges can be retrived
  Scenario: client makes call to GET /badges
    When the client calls /badges
    Then the client receives status code of 403