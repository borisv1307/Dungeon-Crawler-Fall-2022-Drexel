@IntegrationTest
Feature: Move the enemy into wall

  Scenario Outline: Move into enemy
    Given the level design is:
      | XXX |
      | XEX |
      | XXX |
    When the enemy moves <direction>
    Then the enemy is located at (2, 2)
    Examples:
      | direction |
      | left      |
      | right     |
      | up        |
      | down      |