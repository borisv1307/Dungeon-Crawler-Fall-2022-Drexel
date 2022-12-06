@IntegrationTest
Feature: Remove an object from the board

  Background:
    Given the design for the level is:
      | XXXXX |
      | X O X |
      | X P X |
      | X   X |
      | XXXXX |


  Scenario: Remove an object from tile (2,1)
    When the game has been active for 5 seconds
    Then the object at (2,1) will be removed

  Scenario: Move up into object
    When an object is located at (2,1)
    When the player moves up to that tile
    Then the player will be located at (2,1)