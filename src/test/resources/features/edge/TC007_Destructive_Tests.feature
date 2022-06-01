Feature: Attempting to break the API to check robustness

  Background:
    Given user is authorized to access for destructive tests

  Scenario: Create issue with overflow title
    When user puts more than maximum characters to issue title of project 31424783
    Then should see message "is too long (maximum is 255 characters)"

  Scenario: List issue with invalid issue id
    When user wants to get issue using invalid issue id of project 29174825
    Then should see "issue_iid is invalid"