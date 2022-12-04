@Adhoc
Feature: Move the player into empty space

  Background: 
    Given the level design is:
      | XXXXX |
      | X   X |
      | X P X |
      | X   X |
      | XXXXX |

  Scenario: Move left into empty space
    When the player moves left
    Then the player is located at (2, 3)

  Scenario: Move right into empty space
    When the player moves right
    Then the player is located at (4, 3)

  Scenario: Move up into empty space
    When the player moves up
    Then the player is located at (3, 2)

  Scenario: Move down into empty space
    When the player moves down
    Then the player is located at (3, 4)
