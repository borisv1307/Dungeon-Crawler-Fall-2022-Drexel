@IntegrationTest
Feature: Create a Level read from a file

  Scenario: Level 1
    Given level is:
      | 6      |
      | 3      |
      | XXXXXX |
      | X PEGX |
      | XXXXXX |
    When I create the level
    Then starting from the top-left:
    And the player's x coordinate is 3
    And the player's y coordinate is 2
    And the enemy's x coordinate is 4
    And the enemy's y coordinate is 2
    And the goal's x coordinate is 5
    And the goal's y coordinate is 2
    And (1, 1) is "X"
    And (2, 1) is "X"
    And (3, 1) is "X"
    And (4, 1) is "X"
    And (5, 1) is "X"
    And (6, 1) is "X"
    And (1, 2) is "X"
    And (2, 2) is " "
    And (3, 2) is "P"
    And (4, 2) is "E"
    And (5, 2) is "G"
    And (6, 2) is "X"
    And (1, 3) is "X"
    And (2, 3) is "X"
    And (3, 3) is "X"
    And (4, 3) is "X"
    And (5, 3) is "X"
    And (6, 3) is "X"

  Scenario: Invalid level
    Given level is:
      | 4    |
      | 3    |
      | XXXX |
      | X&PX |
      | XXXX |
    When I create the level
    Then the error message is displayed
    And the message is: "Invalid character provided: &"

  Scenario: Missing a dimension
    Given level is:
      | 4    |
      | XXXX |
      | X&PX |
      | XXXX |
    When I create the level
    Then the error message is displayed
    And the message is: "For input string: "XXXX""

  Scenario: Missing both dimensions
    Given level is:
      | XXXX |
      | X&PX |
      | XXXX |
    When I create the level
    Then the error message is displayed
    And the message is: "For input string: "XXXX""

  Scenario: Invalid level
    Given level is:
      | 4    |
      | 3    |
      | XXXX |
      | X+PX |
      | XXXX |
    When I create the level
    Then the error message is displayed
    And the message is: "Invalid character provided: +"

  Scenario: The file reader malfunctions
    Given level is:
      | 4    |
      | 3    |
      | XXXX |
      | X+PX |
      | XXXX |
    When I create the level with malfunctioning reader
    Then the malfunctioning reader error message is displayed