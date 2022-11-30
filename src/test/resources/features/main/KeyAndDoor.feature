@IntegrationTest
Feature: Key and door feature

  Background:
    Given the level design is:
      | XKX |
      | XPX |
      | EDG |

  Scenario: Without a key, the player shouldn't be able to open the door
    When the player moves down
    Then the player is located at (2, 2)

  Scenario: Player moves and obtains the key
    When the player moves up
    Then the player is located at (2, 1)
    Then the player has key

  Scenario: After receiving the key, the player must be able to open the door
    When the player moves up
    Then the player is located at (2, 1)
    Then the player has key
    When the player moves down
    When the player moves down
    Then the player is located at (2, 3)
