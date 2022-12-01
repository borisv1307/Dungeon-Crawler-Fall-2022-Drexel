@Adhoc
Feature: Add an object to an empty tile

  Background:
    Given the level design is:
      | XXXX |
      | X  X |
      | X PX |
      | X  X |
      | XXXX |

  Scenario: Add an object to tile (1,1)
    When (1,1) is selected as the randomly passable tile
    Then an object will be located at (1,1)

  Scenario: Add an object to a random passable tile
    When a tile is passable and randomly selected
    Then an object will be placed on that tile

  Scenario: After 10 seconds objects will be placed
    When the game has been active for 10 seconds
    Then there will be objects available within the level