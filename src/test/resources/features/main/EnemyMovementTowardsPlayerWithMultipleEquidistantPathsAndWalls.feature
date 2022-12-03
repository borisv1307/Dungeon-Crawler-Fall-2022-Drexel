@IntegrationTest
Feature: Move the enemy toward player without walls inside layer when two paths are equidistant from player and goal with walls

  Scenario: Move enemy on path closest to goal when 2 paths are equidistant to player
    Given the level 1 design is:
      | 7       |
      | 5       |
      | XXXXXXX |
      | X E   X |
      | X X  GX |
      | X P   X |
      | XXXXXXX |
    When the enemy moves
    Then the enemy is located at (4, 2)

  Scenario: Move enemy on path closest to goal when 2 paths are equidistant to player
    Given the level 1 design is:
      | 7       |
      | 5       |
      | XXXXXXX |
      | X   E X |
      | XG  X X |
      | X   P X |
      | XXXXXXX |
    When the enemy moves
    Then the enemy is located at (4, 2)

  Scenario: Move enemy on path closest to goal when 2 paths are equidistant to player with multiple walls
    Given the level 1 design is:
      | 7       |
      | 5       |
      | XXXXXXX |
      | X   E X |
      | XGX X X |
      | X X P X |
      | XXXXXXX |
    When the enemy moves
    Then the enemy is located at (4, 2)