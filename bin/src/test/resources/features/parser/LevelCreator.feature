@IntegrationTest
Feature: Create a Level

  Scenario: Level 1
    Given level is:
      | XXXX |
      | X PX |
      | XXXX |
    When I create the level
    Then starting from the top-left:
    And the player's x coordinate is 3
    And the player's y coordinate is 2
    And (1, 1) is "X"
    And (2, 1) is "X"
    And (3, 1) is "X"
    And (4, 1) is "X"
    And (1, 2) is "X"
    And (2, 2) is " "
    And (3, 2) is " "
    And (4, 2) is "X"
    And (1, 3) is "X"
    And (2, 3) is "X"
    And (3, 3) is "X"
    And (4, 3) is "X"

  Scenario: Invalid level
    Given level is:
      | XXXX |
      | X&PX |
      | XXXX |
    When I create the level
    Then the invalid character error message is displayed
    And the message is: "Invalid character provided: &"

  Scenario: Invalid level
    Given level is:
      | XXXX |
      | X+PX |
      | XXXX |
    When I create the level
    Then the invalid character error message is displayed
    And the message is: "Invalid character provided: +"

  Scenario: The file reader malfunctions
    Given level is:
      | XXXX |
      | X+PX |
      | XXXX |
    When I create the level with malfunctioning reader
    Then the malfunctioning reader error message is displayed
