@Ignore
Feature: Move the player into Goal

  Scenario: Move player right into goal
    Given the level design is:
      | 4    |
      | 3    |
      | XXXX |
      | XPGX |
      | XXXX |
    When the player moves right
    Then level 2 is generated
    And win count is 1
    And lose count is 0

  Scenario: Move player left into goal
    Given the level design is:
      | 4    |
      | 3    |
      | XXXX |
      | XGPX |
      | XXXX |
    When the player moves left
    Then level 2 is generated
    And win count is 1
    And lose count is 0

  Scenario: Move player up into goal
    Given the level design is:
      | 3   |
      | 4   |
      | XXX |
      | XGX |
      | XPX |
      | XXX |
    When the player moves up
    Then level 2 is generated
    And win count is 1
    And lose count is 0

  Scenario: Move player down into goal
    Given the level design is:
      | 3   |
      | 4   |
      | XXX |
      | XPX |
      | XGX |
      | XXX |
    When the player moves down
    Then level 2 is generated
    And win count is 1
    And lose count is 0