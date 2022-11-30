@IntegrationTest
Feature: Achieve goal feature

  Background:
    Given the level design is:
      | XKX |
      | XPX |
      | XDG |

  Scenario: After acquiring the key, the player must be able to unlock the door and achieve the goal
    When the player moves up
    Then the player is located at (2, 1)
    Then the player has key
    When the player moves down
    When the player moves down
    Then the player is located at (2, 3)
    When the player moves right
    Then the player has achieved the goal
