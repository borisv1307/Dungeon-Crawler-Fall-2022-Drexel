@IntegrationTest
Feature: Move the player into empty space

  Background:
    Given the level design is:
      | XXXXXXXXXXXXXXXX |
      | X              X |
      | X              X |
      | X      P       X |
      | XXXXXXXXXXXXXXXX |

  Scenario: Move player into bomb
    When the player hits bomb
    Then bomb and player same coordinate