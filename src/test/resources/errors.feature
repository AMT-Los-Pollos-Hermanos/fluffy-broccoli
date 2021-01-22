Feature: Actions that imply errors

  Scenario: the client can't GET /application
    When the client get "/applications"
    Then the client receives status code of 403

  Scenario: the client can't PUT /application
    When the client get "/applications"
    Then the client receives status code of 403

  Scenario: the client can't DELETE /application
    When the client get "/applications"
    Then the client receives status code of 403

  Scenario: the client can't GET /badges with no application
    When the client get "/badges"
    Then the client receives status code of 403

  Scenario: the client can't POST /badges with no application
    When the client posts /badges
    Then the client receives status code of 403
    
  Scenario: the client can't DELETE /badges/1 with no application
    When the client delete "/badges/1"
    Then the client receives status code of 403
    
  Scenario: the client can't PUT /badges/1 with no application
    When the client put "/badges/1"
    Then the client receives status code of 403

  Scenario: the client can't GET /badges with wrong API-KEY
    Given an application named "test"
    When the client get "/badges" with wrong API-KEY
    Then the client receives status code of 403

  Scenario: the client can't DELETE /badges/1 with wrong API-KEY
    Given an application named "test"
    When the client delete "/badges/1" with wrong API-KEY
    Then the client receives status code of 403

  Scenario: the client can't PUT /badges/1 with wrong API-KEY
    Given an application named "test"
    When the client put "/badges/1" with wrong API-KEY
    Then the client receives status code of 403

  Scenario: the client can't GET /rules with no application
    When the client get "/rules"
    Then the client receives status code of 403

  Scenario: the client can't GET /rules/1 with no application
    When the client get "/rules/1"
    Then the client receives status code of 403

  Scenario: the client can't DELETE /rules/1 with no application
    When the client delete "/rules/1"
    Then the client receives status code of 403

  Scenario: the client can't PUT /rules/1 with no application
    When the client put "/rules/1"
    Then the client receives status code of 403
