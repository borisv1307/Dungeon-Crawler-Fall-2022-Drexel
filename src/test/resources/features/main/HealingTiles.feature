@IntegrationTest
Feature: Gain HP when stepping onto Healing Tiles

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

  Scenario: Move right into healing space
    When the player moves right
    Then the player's HP is 20

  Scenario: Move up into healing space
    When the player moves up
    Then the player's HP is 20

  Scenario: Move down into healing space
    When the player moves down
    Then the player's HP is 20

  Scenario: Move down, then left into 2 healing spaces
    When the player moves down and left
    Then the player's HP is 30

  Scenario: Move in a circle across all healing spaces
    When the player moves in a circle
    Then the player's HP is 50