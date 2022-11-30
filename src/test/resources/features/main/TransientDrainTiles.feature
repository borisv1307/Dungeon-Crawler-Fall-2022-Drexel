@IntegrationTest
Feature: Lose HP over multiple steps after stepping onto Drain Tiles

  Background:
    Given the level design is:
      | XXXXX |
      | X   X |
      | X P X |
      | X S X |
      | XXXXX |

  Scenario: Move down into drain space
    When the player moves down
    Then the player's HP is 10
    And the player is on a PASSABLE tile
    And the player has drain on
    And the drain counter is 5

  Scenario: Move right into empty space
    When the player moves right
    Then the player's HP is 10
    And the player is on a PASSABLE tile
    And the player has drain off
    And the drain counter is 0

  Scenario: Move player down onto drain space, then left into empty space
    When the player moves down and left
    Then the player's HP is 8
    And the player is on a PASSABLE tile
    And the player has drain on
    And the drain counter is 4

  Scenario: Move in a circle, starting at 12:00
    When the player moves in a circle
    Then the player's HP is 2
    And the player is on a PASSABLE tile
    And the player has drain on
    And the drain counter is 1

  Scenario: Move in a circle, starting at 6:00
    When the player moves in a circle after moving down
    Then the player's HP is 0
    And the player is on a PASSABLE tile
    And the player has drain off
    And the drain counter is 0