@IntegrationTest
Feature: Gain HP when stepping onto Transient Healing Tiles

  Background:
    Given the level design is:
      | XXXXX  |
      | XMMMMX |
      | XMPMX  |
      | XMMMX  |
      | XXXXX  |

  Scenario: Move left into transient healing space
    When the player moves left
    Then the player's HP is 15
    And the player is on a PASSABLE tile


  Scenario: Move right into transient healing space
    When the player moves right
    Then the player's HP is 15
    And the player is on a PASSABLE tile

  Scenario: Move up into transient healing space
    When the player moves up
    Then the player's HP is 15
    And the player is on a PASSABLE tile

  Scenario: Move down into transient healing space
    When the player moves down
    Then the player's HP is 15
    And the player is on a PASSABLE tile

  Scenario: Move down, then left into 2 transient healing spaces
    When the player moves down and left
    Then the player's HP is 20
    And the player is on a PASSABLE tile

  Scenario: Move in a circle across all transient healing spaces
    When the player moves in a circle
    Then the player's HP is 50
    And the player is on a PASSABLE tile
