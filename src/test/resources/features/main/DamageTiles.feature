@IntegrationTest
Feature: Lose HP when stepping onto Damage Tiles

  Background:
    Given the level design is:
      | XXXXX |
      | XDDDX |
      | XDPDX |
      | XDDDX |
      | XXXXX |

  Scenario: Move left into damage space
    When the player moves left
    Then the player's HP is 5

  Scenario: Move right into damage space
    When the player moves right
    Then the player's HP is 5

  Scenario: Move up into damage space
    When the player moves up
    Then the player's HP is 5

  Scenario: Move down into damage space
    When the player moves down
    Then the player's HP is 5

  Scenario: Move down, then left into 2 damage spaces
    When the player moves down and left
    Then the player's HP is 0

  Scenario: Move in a circle across all damage spaces
    When the player moves in a circle
    Then the player's HP is 0