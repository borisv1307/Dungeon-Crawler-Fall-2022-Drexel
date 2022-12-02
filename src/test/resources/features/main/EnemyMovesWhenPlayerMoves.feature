@IntegrationTest
Feature: Move the enemy when player moves

  Background:
    Given the level design with enemy focus is:
      | XXXXXX |
      | XEX  X |
      | X   PX |
      | XXXXXX |

  Scenario: Player moves into wall
    When the player tries to move right
    Then the enemy is not located at (2, 2)

  Scenario: Player moves into empty space
    When the player tries to move up
    Then the enemy is not located at (2, 2)