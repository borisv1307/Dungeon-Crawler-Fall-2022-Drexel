@IntegrationTest
Feature: Move the player over a coin and collect it


  Background:
    Given the level design is:
      | XXXXX |
      | X C X |
      | X P X |
      | X   X |
      | XXXXX |

  Scenario: Moves onto coin with 0 coins
    Given the coin is located at (3, 2)
      And the player has 0 coins
    When the player moves up
    Then the player is located at (3, 2)
      And the player has 1 coins

  Scenario: Moves onto coin with 4 coins (Requires 5 to level up to 2)
    Given the coin is located at (3, 2)
      And the player has 4 coins
      And the player is at level 1
    When the player moves up
    Then the player is located at (3, 2)
      And the player has 5 coins
      And the player is at level 2

  Scenario: Moves onto coin with 9 coins (Requires 10 to level up to 3)
    Given the coin is located at (3, 2)
      And the player has 9 coins
      And the player is at level 2
    When the player moves up
    Then the player is located at (3, 2)
      And the player has 10 coins
      And the player is at level 3