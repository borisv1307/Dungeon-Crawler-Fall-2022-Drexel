@IntegrationTest
Feature: Move the player into portal and transport to an empty space

  Background:
    Given the portal level design is:
      | XXX |
      | X X |
      | XPX |
      | XOX |
      | XXX |

  Scenario: Move player into portal
    When the portal is located at (2, 4)
    And the empty space is located at (2, 2)
    And the player moves down to portal
    Then the player is transported to (2, 2)
