@IntegrationTest
Feature: Move the enemy toward player without obstacles

  Scenario: Move enemy towards player on shortest path (y move) (moves down when priority in a tie is up)
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X   X |
      | XEG X |
      | X  PX |
      | XXXXX |
    When the enemy moves
    Then the enemy is located at (2, 4)

  Scenario: Move enemy towards player on shortest path (x move) (moves right when priority in a tie is left)
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X E X |
      | X G X |
      | X  PX |
      | XXXXX |
    When the enemy moves
    Then the enemy is located at (4, 2)

  Scenario: Move enemy on path closest to goal when 2 paths are equidistant to player
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X   X |
      | XE GX |
      | X  PX |
      | XXXXX |
    When the enemy moves
    Then the enemy is located at (3, 3)

  Scenario: Move enemy towards player on shortest path when blocked (y move)
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X   X |
      | XEG X |
      | X XPX |
      | XXXXX |
    When the enemy moves
    Then the enemy is located at (2, 2)

  Scenario: Move enemy towards player on shortest path when blocked (x move)
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X E X |
      | X GXX |
      | X  PX |
      | XXXXX |
    When the enemy moves
    Then the enemy is located at (2, 2)

  Scenario: Enemy doesn't move when surrounded by obstacles
    Given the level 1 design is:
      | 7       |
      | 5       |
      | XXXXXXX |
      | XXEG  X |
      | X X   X |
      | X P   X |
      | XXXXXXX |
    When the enemy moves
    Then the enemy is located at (3, 2)