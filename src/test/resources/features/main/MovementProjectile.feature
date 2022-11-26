@IntegrationTest
Feature: Move the player into projectile

  Background:
    Given the level design is:
      | XXX |
      | X X |
      | XOX |
      | XPX |
      | XXX |

  Scenario: Move up into a friendly projectile
    When the player moves up twice
    Then the player is then located at (1, 1)
