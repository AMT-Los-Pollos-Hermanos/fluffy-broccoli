Feature: Badges can be retrived
  Scenario: client makes call to GET /badges
    When the client get "/badges"
    Then the client receives status code of 403