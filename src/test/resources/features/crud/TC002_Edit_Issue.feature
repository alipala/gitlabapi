Feature: Edit issue
  Scenario: User is able to update an issue
    Given issue 1 already exist in project 29174825
    When user wants to update issue
    Then see the updated issue content