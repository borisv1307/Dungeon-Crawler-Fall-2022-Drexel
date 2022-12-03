@IntegrationTest
Feature: Move the player into empty space

  Background:
    Given the level design is:
      | XXXXXXXXXXXXXXXXXXXX |
      | X                  X |
      | X                  X |
      | X      P           X |
      | XXXXXXXXXXXXXXXXXXXX |

  Scenario: Move player into bomb
    When the player hits bomb
    Then bomb and player same coordinate