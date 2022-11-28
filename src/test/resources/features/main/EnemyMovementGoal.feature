@Ignore
Feature: Move the enemy into Goal

  Scenario: Move enemy right into goal
    Given the level design is:
      | XXXX |
      | XEGX |
      | XXXX |
    When the enemy moves right
    Then the enemy is located at (2, 2)

    Scenario: Move enemy left into goal
      Given the level design is:
        | XXXX |
        | XGEX |
        | XXXX |
      When the enemy moves left
      Then the enemy is located at (3, 2)

  Scenario: Move enemy up into goal
    Given the level design is:
      | XXX |
      | XGX |
      | XEX |
      | XXX |
    When the enemy moves up
    Then the enemy is located at (2, 3)

  Scenario: Move enemy down into goal
    Given the level design is:
      | XXX |
      | XEX |
      | XGX |
      | XXX |
    When the enemy moves down
    Then the enemy is located at (2, 2)