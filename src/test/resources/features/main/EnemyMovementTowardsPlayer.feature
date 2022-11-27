Feature: Move the enemy toward player without obstacles

  Scenario: Move enemy towards player when x and y are equidistant
    Given the level design is:
      | XXXXX |
      | X   X |
      | X E X |
      | X  PX |
      | XXXXX |
    When the enemy moves
    Then the enemy is located at (3, 4) or (4, 3)

  Scenario: Move enemy towards player when x difference is larger than y
    Given the level design is:
      | XXXXX |
      | X E X |
      | X   X |
      | X  PX |
      | XXXXX |
    When the enemy moves
    Then the enemy is located at (3, 3)

  Scenario: Move enemy towards player when y difference is larger than x
    Given the level design is:
      | XXXXX |
      | X   X |
      | XE  X |
      | X  PX |
      | XXXXX |
    When the enemy moves
    Then the enemy is located at (3, 3)