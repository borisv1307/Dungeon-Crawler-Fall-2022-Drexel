@IntegrationTest
Feature: Lose HP when stepping onto Transient Damage Tiles

  Background:
    Given the level design is:
      | XXXXX |
      | XBBBX |
      | XBPBX |
      | XBBBX |
      | XXXXX |

  Scenario: Move left into transient healing space
    When the player moves left
    Then the player's HP is 5
    And the player is on a PASSABLE tile


  Scenario: Move right into transient healing space
    When the player moves right
    Then the player's HP is 5
    And the player is on a PASSABLE tile

  Scenario: Move up into transient healing space
    When the player moves up
    Then the player's HP is 5
    And the player is on a PASSABLE tile

  Scenario: Move down into transient healing space
    When the player moves down
    Then the player's HP is 5
    And the player is on a PASSABLE tile

  Scenario: Move down, then left into 2 transient healing spaces
    When the player moves down and left
    Then the player's HP is 0
    And the player is on a PASSABLE tile

  Scenario: Move in a circle across all transient healing spaces
    When the player moves in a circle
    Then the player's HP is 0
    And the player is on a PASSABLE tile