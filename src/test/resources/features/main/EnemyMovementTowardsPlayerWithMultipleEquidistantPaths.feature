@IntegrationTest
Feature: Move the enemy toward player without walls inside layer when two paths are equidistant from player and goal

  Scenario: Move enemy towards player between left and right prioritizes left in a tie
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X E X |
      | X G X |
      | X P X |
      | XXXXX |
    When the enemy moves
    Then the enemy is located at (2, 2)

  Scenario: Move enemy towards player between up and down prioritizes down in a tie
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X   X |
      | XEGPX |
      | X   X |
      | XXXXX |
    When the enemy moves
    Then the enemy is located at (2, 4)

  Scenario: Move enemy towards player between down and right prioritizes right in a tie
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | XE  X |
      | X G X |
      | X  PX |
      | XXXXX |
    When the enemy moves
    Then the enemy is located at (3, 2)

  Scenario: Move enemy towards player between down and left prioritizes left in a tie
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X  EX |
      | X G X |
      | XP  X |
      | XXXXX |
    When the enemy moves
    Then the enemy is located at (3, 2)

  Scenario: Move enemy towards player between right and up prioritizes right in a tie
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X  PX |
      | X G X |
      | XE  X |
      | XXXXX |
    When the enemy moves
    Then the enemy is located at (3, 4)

  Scenario: Move enemy towards player between right and up prioritizes right in a tie
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | XP  X |
      | X G X |
      | X  EX |
      | XXXXX |
    When the enemy moves
    Then the enemy is located at (3, 4)