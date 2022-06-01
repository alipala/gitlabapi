Feature: JSON Schema Validation

  Scenario: Schema should be valid
    Given user is authorized to validation
    When user request to get an issue json format
    Then request should be valid
