Feature: Badges can be retrieved

  Background:
    Given an application named "test"
    And the client receives an API-KEY

  Scenario: the client makes call to GET /badges with existing application and no posted badge
    When the client get "/badges" with API-KEY
    Then the client receives status code of 200
    And the client receives an empty array of badges

  Scenario: the client makes call to GET /badges with existing application
    When the client posts /badges 5 times
    Then the client receives status code of 201
    When the client get "/badges" with API-KEY
    Then the client receives an array of 5 badges
    And the client receives status code of 200

  Scenario: the client makes call to GET /badges/6, PUT /badges/6 and DELETE /badges/6 with existing application
    When the client posts "/badges"
    Then the client receives status code of 201
    When the client get "/badges/6" with API-KEY
    Then the client receives a badge
    And the client receives status code of 200
    
    When the client put "/badges/6" with API-KEY
    Then the client receives status code of 200
    When the client get "/badges/6" with API-KEY
    Then the client has updated a badge
    #TODO Check when merged with master if it works
    When the client delete "/badges/6" with API-KEY
    Then the client receives status code of 204
    #And the client get "/badges/6" with API-KEY
    #Then the client receives status code of 404

