@IntegrationTest
Feature: Move the player into Goal

  Scenario: Move player right into goal
    Given the level 1 design is:
      | 4    |
      | 3    |
      | XXXX |
      | XPGX |
      | XXXX |
    And the level 2 design is:
      | 4    |
      | 5    |
      | XXXX |
      | X  X |
      | X PX |
      | XG X |
      | XXXX |
    When the player moves right
    Then level 2 is generated
    And win count is 1
    And lose count is 0
    And the player is located at (3, 3)
    And the goal is located at (2, 4)

  Scenario: Move player left into goal
    Given the level 1 design is:
      | 4    |
      | 3    |
      | XXXX |
      | XGPX |
      | XXXX |
    And the level 2 design is:
      | 4    |
      | 5    |
      | XXXX |
      | X  X |
      | X PX |
      | XG X |
      | XXXX |
    When the player moves left
    Then level 2 is generated
    And win count is 1
    And lose count is 0
    And the player is located at (3, 3)
    And the goal is located at (2, 4)

  Scenario: Move player up into goal
    Given the level 1 design is:
      | 3   |
      | 4   |
      | XXX |
      | XGX |
      | XPX |
      | XXX |
    And the level 2 design is:
      | 4    |
      | 5    |
      | XXXX |
      | X  X |
      | X PX |
      | XG X |
      | XXXX |
    When the player moves up
    Then level 2 is generated
    And win count is 1
    And lose count is 0
    And the player is located at (3, 3)
    And the goal is located at (2, 4)

  Scenario: Move player down into goal
    Given the level 1 design is:
      | 3   |
      | 4   |
      | XXX |
      | XPX |
      | XGX |
      | XXX |
    And the level 2 design is:
      | 4    |
      | 5    |
      | XXXX |
      | X  X |
      | X PX |
      | XG X |
      | XXXX |
    When the player moves down
    Then level 2 is generated
    And win count is 1
    And lose count is 0
    And the player is located at (3, 3)
    And the goal is located at (2, 4)