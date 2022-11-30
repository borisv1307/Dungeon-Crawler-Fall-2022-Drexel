@IntegrationTest
Feature: Gain HP over multiple steps after stepping onto Regen Tiles

  Background:
    Given the level design is:
      | XXXXX |
      | X   X |
      | X P X |
      | X F X |
      | XXXXX |

  Scenario: Move down into regen space
    When the player moves down
    Then the player's HP is 10
    Then the player is on a PASSABLE tile
    Then the player has regen on
    Then the regen counter is 5

  Scenario: Move right into empty space
    When the player moves right
    Then the player's HP is 10
    Then the player is on a PASSABLE tile
    Then the player has regen off
    Then the regen counter is 0

  Scenario: Move player down onto regen space, then left into empty space
    When the player moves down and left
    Then the player's HP is 12
    Then the player is on a PASSABLE tile
    Then the player has regen on
    Then the regen counter is 4

  Scenario: Move in a circle, starting at 12:00
    When the player moves in a circle
    Then the player's HP is 18
    Then the player is on a PASSABLE tile
    Then the player has regen on
    Then the regen counter is 1

  Scenario: Move in a circle, starting at 6:00
    When the player moves in a circle after moving down
    Then the player's HP is 20
    Then the player is on a PASSABLE tile
    Then the player has regen off
    Then the regen counter is 0