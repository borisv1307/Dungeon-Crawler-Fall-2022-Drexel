@IntegrationTest
Feature: Retain Health Value While Moving the player into empty spaces

  Background:
    Given the level design is:
      | XXXXX |
      | X   X |
      | X P X |
      | X   X |
      | XXXXX |

  Scenario: Move left into empty space
    When the player moves left
    Then the player's HP is 10

  Scenario: Move right into empty space
    When the player moves right
    Then the player's HP is 10

  Scenario: Move up into empty space
    When the player moves up
    Then the player's HP is 10

  Scenario: Move down into empty space
    When the player moves down
    Then the player's HP is 10