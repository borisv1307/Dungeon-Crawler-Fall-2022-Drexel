@Adhoc
Feature: Add an object to an empty tile

  Background:
    Given the design for the level is:
      | XXXXX |
      | X   X |
      | X P X |
      | X   X |
      | XXXXX |

  Scenario: Add an object to an empty tile
    When a tile is empty and there is an object available
    Then an object will be placed on that tile

  Scenario: Add two objects to two empty tiles
    When two tiles are empty and there are two objects available
    Then one object will be placed on each tile

  Scenario: After 10 seconds an object will be placed
    When the game has been active for 10 seconds
    Then an object will be placed on a passable tile