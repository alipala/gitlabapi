Feature: Edge tests

  Scenario: Unauthorized access attempt
    Given user is unauthorized
    When user wants to retrieve issue id 10 in project 30003680
    Then should get "401 Unauthorized" in response

  Scenario: Update an issue for a project does not exist
    Given user is authorized
    When user wants to update an issue undefined project
    Then should see "404 Project Not Found" in response
