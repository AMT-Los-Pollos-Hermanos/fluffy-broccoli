Feature: Testing rules
  Scenario: Get all rules
    When the client get all rules
    Then the client receives a 200