@Ignore
Feature: Move the enemy toward player without obstacles

  Scenario: Move enemy towards player on shortest path (y move)
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X   X |
      | XEG X |
      | X  PX |
      | XXXXX |
    When the enemy moves
    Then the enemy is located at (3, 4)

  Scenario: Move enemy towards player on shortest path (x move)
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
