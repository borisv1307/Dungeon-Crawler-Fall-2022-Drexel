@IntegrationTest
Feature: Move the player into empty space

  Background:
    Given the level design is:
      | XXXXX |
      | X   X |
      | X P X |
      | X   X |
      | XXXXX |

  Scenario Outline: Move into empty space
    When the player moves <direction>
    Then the player is located at <point>
    Examples:
      | direction | point  |
      | left      | (2, 3) |
      | right     | (4, 3) |
      | up        | (3, 2) |
      | down      | (3, 4) |