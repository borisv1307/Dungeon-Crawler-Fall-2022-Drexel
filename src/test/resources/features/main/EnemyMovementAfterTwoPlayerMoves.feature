@IntegrationTest
Feature: Move the enemy after player moves twice

  Scenario: Move right after player moves twice
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | XP  X |
      | X E X |
      | X   X |
      | XXXXX |
    When the player moves right
    When the player moves right
    Then the enemy is located at (4, 3)
    And the player is located at (4, 2)

  Scenario: Count Player moves that result in no movement
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | XP  X |
      | X E X |
      | X   X |
      | XXXXX |
    When the player moves up
    When the player moves up
    Then the enemy is located at (4, 3)
    And the player is located at (2, 2)

  Scenario: Count Player moves that result in no movement
    Given the level 1 design is:
      | 6      |
      | 5      |
      | XXXXXX |
      | XP   X |
      | X E  X |
      | X    X |
      | XXXXXX |
    When the player moves down
    When the player moves down
    When the player moves right
    When the player moves right
    Then the enemy is located at (5, 3)
    And the player is located at (4, 4)
