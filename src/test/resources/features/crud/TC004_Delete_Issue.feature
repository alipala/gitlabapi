@DAV-T4
Feature: Delete an issue

  Background:
    Given user is authorized to access to delete

  Scenario: Admin is able to delete issue
    Given user retrieve an issue
    When user wants to delete the issue
    Then the issue should not exist