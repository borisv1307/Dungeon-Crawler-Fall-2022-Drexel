@IntegrationTest
Feature: Add an object to the board

  Background:
    Given the design for the level is:
      | XXXXX |
      | X   X |
      | X P X |
      | X   X |
      | XXXXX |


  Scenario: Add an object to tile (1,1)
    When (1,1) is selected as the randomly passable tile
    Then an object will be located at (1,1)

  Scenario: Add an object to tile (1,1) and (1,4)
    When 2 is returned as the random number of objects
    And (1,1) is selected as the randomly passable tile
    Then an object will be located at (1,1)
    When (1,4) is selected as the randomly passable tile
    Then an object will be located at (1,4)

  Scenario: After 10 seconds objects will be placed
    When the game has been active for 5 seconds
    And (1,1) is selected as the randomly passable tile
    Then an object will be located at (1,1)