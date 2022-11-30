@IntegrationTest
Feature: Achieve goal feature

  Background:
    Given the level design is:
      | XKX |
      | XPX |
      | XDG |

  Scenario: After achieving goal of current level, game should launch new level
    When the current level is 1
    When the player moves up
    Then the player is located at (2, 1)
    Then the player has key
    When the player moves down
    When the player moves down
    Then the player is located at (2, 3)
    When the player moves right
    Then the current level is 2

  Scenario: Player should not be able to access door without key not next level
    When the current level is 1
    When the player moves up
    Then the player is located at (2, 1)
    Then the player has key
    When the player moves down
    When the player moves down
    Then the player is located at (2, 3)
    When the player moves right
    Then the current level is 2
    Then the player does not have key