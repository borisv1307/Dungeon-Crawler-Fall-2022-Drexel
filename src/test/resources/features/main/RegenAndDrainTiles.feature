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
    And the player is on a REGEN tile
    And the player has regen on
    And the regen counter is 5
    And the player has drain off
    And the drain counter is 0

  Scenario: Move right into empty space
    When the player moves right
    Then the player's HP is 10
    And the player is on a PASSABLE tile
    And the player has regen off
    And the regen counter is 0
    And the player has drain off
    And the drain counter is 0

  Scenario: Move player down onto regen space, then left into empty space
    When the player moves down and left
    Then the player's HP is 12
    And the player is on a PASSABLE tile
    And the player has regen on
    And the regen counter is 4
    And the player has drain off
    And the drain counter is 0

  Scenario: Move in a circle, starting at 12:00
    When the player moves in a circle
    Then the player's HP is 8
    And the player is on a DRAIN tile
    And the player has regen on
    And the regen counter is 1
    And the player has drain on
    And the drain counter is 5

  Scenario: Move in a circle, starting at 6:00
    When the player moves in a circle after moving down
    Then the player's HP is 12
    And the player is on a REGEN tile
    And the player has regen on
    And the regen counter is 5
    And the player has drain on
    And the drain counter is 1

  Scenario: Move down onto regen space, then in a circle, stopping before the regen space
    When the player moves down, then around
    Then the player's HP is 14
    And the player is on a PASSABLE tile
    And the player has regen off
    And the regen counter is 0
    And the player has drain on
    And the drain counter is 2