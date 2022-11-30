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
    And the player is on a PASSABLE tile
    And the player has regen on
    And the regen counter is 5

  Scenario: Move right into empty space
    When the player moves right
    Then the player's HP is 10
    And the player is on a PASSABLE tile
    And the player has regen off
    And the regen counter is 0

  Scenario: Move player down onto regen space, then left into empty space
    When the player moves down and left
    Then the player's HP is 12
    And the player is on a PASSABLE tile
    And the player has regen on
    And the regen counter is 4

  Scenario: Move in a circle, starting at 12:00
    When the player moves in a circle
    Then the player's HP is 18
    And the player is on a PASSABLE tile
    And the player has regen on
    And the regen counter is 1

  Scenario: Move in a circle, starting at 6:00
    When the player moves in a circle after moving down
    Then the player's HP is 20
    And the player is on a PASSABLE tile
    And the player has regen off
    And the regen counter is 0