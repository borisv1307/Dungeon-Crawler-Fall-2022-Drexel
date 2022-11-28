@IntegrationTest
Feature: Given key, door and collectible

  Background:
    Given the level design is:
      | XXKXX |
      | X   X |
      | X P X |
      | X C X |
      | XXDXX |

  Scenario: If player has no key door cannot be opened
    When the player moves down
    When the player moves down
    Then the player is located at (3,4)

  Scenario: Player moves and gets collectible
    When the player moves down
    Then the player is located at (3,4)
    Then the player has the key

  Scenario: Player moves and gets key
    When the player moves up
    When the player moves up
    Then the player is located at (3,1)
    Then the player has the key

  Scenario: After key gotten, the player can open door
    When the player moves up
    Then the player is located at (3, 2)

  Scenario: Move down into empty space
    When the player moves up
    When the player moves up
    Then the player is located at (3,1)
    Then the player has the key
    When the player moves down
    When the player moves down
    When the player moves down
    Then the player is located at (3,5)
