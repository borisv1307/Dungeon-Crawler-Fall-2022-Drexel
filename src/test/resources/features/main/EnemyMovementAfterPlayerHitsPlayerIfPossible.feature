@IntegrationTest
Feature: Move the enemy after player moves twice into player

  Scenario: Enemy moves right into player
    Given the level 1 design is:
      | 6      |
      | 5      |
      | XXXXXX |
      | X   PX |
      | X E  X |
      | XG   X |
      | XXXXXX |
    When the player moves down
    And the player moves left
    Then level 1 is generated
    And win count is 0
    And lose count is 1
    And the enemy is located at (3, 3)
    And the player is located at (5, 2)

  Scenario: Enemy moves up into player
    Given the level 1 design is:
      | 6      |
      | 5      |
      | XXXXXX |
      | X   PX |
      | X E  X |
      | XG   X |
      | XXXXXX |
    When the player moves left
    And the player moves left
    Then level 1 is generated
    And win count is 0
    And lose count is 1
    And the enemy is located at (3, 3)
    And the player is located at (5, 2)