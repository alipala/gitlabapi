@DAV-T1
Feature: Create issue

  Background:
    Given user is authorized to create issue

  Scenario: User is able to create issue
    When user wants to create new issue with project id 29174825
    Then should see newly created issue