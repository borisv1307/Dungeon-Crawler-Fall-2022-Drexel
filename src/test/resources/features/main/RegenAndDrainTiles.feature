@IntegrationTest
Feature: Gain and Lose HP over multiple steps after stepping onto Regen or Drain Tiles

  Background:
    Given the level design is:
      | XXXXX |
      | X A X |
      | X P X |
      | X R X |
      | XXXXX |

  Scenario: Move down into regen space
    When the player moves down
    Then the player's HP is 10
    Then the player is on a REGEN tile
    Then the player has regen on
    Then the regen counter is 5
    Then the player has drain off
    Then the drain counter is 0

  Scenario: Move right into empty space
    When the player moves right
    Then the player's HP is 10
    Then the player is on a PASSABLE tile
    Then the player has regen off
    Then the regen counter is 0
    Then the player has drain off
    Then the drain counter is 0

  Scenario: Move player down onto regen space, then left into empty space
    When the player moves down and left
    Then the player's HP is 12
    Then the player is on a PASSABLE tile
    Then the player has regen on
    Then the regen counter is 4
    Then the player has drain off
    Then the drain counter is 0

  Scenario: Move in a circle, starting at 12:00
    When the player moves in a circle
    Then the player's HP is 8
    Then the player is on a DRAIN tile
    Then the player has regen on
    Then the regen counter is 1
    Then the player has drain on
    Then the drain counter is 5

  Scenario: Move in a circle, starting at 6:00
    When the player moves in a circle after moving down
    Then the player's HP is 12
    Then the player is on a REGEN tile
    Then the player has regen on
    Then the regen counter is 5
    Then the player has drain on
    Then the drain counter is 1

  Scenario: Move down onto regen space, then in a circle, stopping before the regen space
    When the player moves down, then around
    Then the player's HP is 14
    Then the player is on a PASSABLE tile
    Then the player has regen off
    Then the regen counter is 0
    Then the player has drain on
    Then the drain counter is 2