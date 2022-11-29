@IntegrationTest
Feature: Move the enemy into empty space

  Scenario Outline: Move into empty
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X   X |
      | X E X |
      | X   X |
      | XXXXX |
    When the enemy moves <direction>
    Then the enemy is located at <point>
    Examples:
      | direction | point  |
      | left      | (2, 3) |
      | right     | (4, 3) |
      | up        | (3, 2) |
      | down      | (3, 4) |