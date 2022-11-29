@IntegrationTest
Feature: Gain HP when stepping onto a Healing Tile

  Background:
    Given the level design is:
      | XXXXX |
      | XHHHX |
      | XHPHX |
      | XHHHX |
      | XXXXX |

  Scenario: Move left into healing space
    When the player moves left
    Then the player's HP is 20

  Scenario: Move right into empty space
    When the player moves right
    Then the player's HP is 20

  Scenario: Move up into empty space
    When the player moves up
    Then the player's HP is 20

  Scenario: Move down into empty space
    When the player moves down
    Then the player's HP is 20

  Scenario: Move down, then left into 2 healing spaces
    When the player moves down and left
    Then the player's HP is 30

  Scenario: Move in a circle to heal to max HP
    When the player moves in a circle
    Then the player's HP is 50