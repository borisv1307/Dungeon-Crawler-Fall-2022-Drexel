@IntegrationTest
Feature: Move the player into portal and transport to an empty space

  Background:
    Given the level design is:
      | XXX |
      | X X |
      | XPX |
      | XOX |
      | XXX |

  Scenario: Move player into portal
    When the player moves down
    Then the player is located at (2, 2)
