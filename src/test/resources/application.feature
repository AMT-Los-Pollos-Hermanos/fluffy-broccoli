Feature: Operations on applications

#  Background:
#    Given There is an application server

  Scenario: the client creates an application
    When the client posts /applications
    Then the client receives status code of 200
    And the client receives an API-KEY




