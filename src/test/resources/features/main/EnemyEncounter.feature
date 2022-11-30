@IntegrationTest
Feature: Enemy encounter feature

  Background:
    Given the level design is:
      | EKX |
      | XPX |
      | XDG |

  Scenario: Restart current level when player encounters enemy
    When the current level is 1
    When the player moves up
    Then the player is located at (2, 1)
    When the player moves left
    Then the current level is 1
    Then the player is located at (2, 2)

  Scenario: Player should not have key once level is restarted
    When the player moves up
    Then the player is located at (2, 1)
    Then the player has key
    When the player moves left
    Then the player is located at (2, 2)
    Then the player does not have key
