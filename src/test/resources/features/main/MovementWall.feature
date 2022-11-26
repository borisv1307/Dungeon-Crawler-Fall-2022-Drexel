@IntegrationTest
Feature: Move the player into wall

  Background:
    Given the level design is:
      | XXX |
      | XPX |
      | XXX |

  Scenario Outline: Move into wall
    When the player moves <direction>
    Then the player is located at (2, 2)
    Examples:
      | direction |
      | left      |
      | right     |
      | up        |
      | down      |