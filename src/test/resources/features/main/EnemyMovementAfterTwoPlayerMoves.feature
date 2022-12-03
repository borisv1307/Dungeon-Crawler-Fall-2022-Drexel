@IntegrationTest
Feature: Move the enemy after player moves twice

  Scenario: Move right after player moves twice
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | XP  X |
      | XG  X |
      | X  EX |
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
      | X  GX |
      | XE  X |
      | XXXXX |
    When the player moves up
    When the player moves up
    Then the enemy is located at (2, 3)
    And the player is located at (2, 2)

  Scenario: Player moving 4 times results in enemy moving 2 times
    Given the level 1 design is:
      | 7       |
      | 5       |
      | XXXXXXX |
      | X  P  X |
      | XG    X |
      | X    EX |
      | XXXXXXX |
    When the player moves down
    When the player moves down
    When the player moves left
    When the player moves left
    Then the enemy is located at (4, 4)
    And the player is located at (2, 4)
