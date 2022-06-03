@DAV-T3
Feature: List authenticated user issues

  Background:
    Given user is authorized to access

  Scenario: User is able to list all issues
    When user wants to get all issues
    Then issues belong to user are returned 18

  Scenario: User is able to list a specific issue
    When user wants to get issue in project 29174825 by "state" equals "closed"
    Then the requested issue is returned
