@IntegrationTest
Feature: Move the player into enemy

  Background:
    Given the level design is:
      | XEX |
      | EPE |
      | XEX |

  Scenario: Move left into enemy
    When the player moves left
    Then the player is located at (2, 2)

  Scenario: Move right into enemy
    When the player moves right
    Then the player is located at (2, 2)

  Scenario: Move up into enemy
    When the player moves up
    Then the player is located at (2, 2)

  Scenario: Move down into enemy
    When the player moves down
    Then the player is located at (2, 2)
