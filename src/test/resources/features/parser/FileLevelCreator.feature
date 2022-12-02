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
    Then the board dimensions are x = 6 and y = 3
    And starting from the top-left:
    And (1, 1) is a Wall
    And (2, 1) is a Wall
    And (3, 1) is a Wall
    And (4, 1) is a Wall
    And (5, 1) is a Wall
    And (6, 1) is a Wall
    And (1, 2) is a Wall
    And (2, 2) is Empty
    And (3, 2) is a Player
    And (4, 2) is an Enemy
    And (5, 2) is a Goal
    And (6, 2) is a Wall
    And (1, 3) is a Wall
    And (2, 3) is a Wall
    And (3, 3) is a Wall
    And (4, 3) is a Wall
    And (5, 3) is a Wall
    And (6, 3) is a Wall

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