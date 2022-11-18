@IntegrationTest
Feature: Move the Projectile in empty Space

  Background:
    Given the level design is:
      | XXXXX |
      | X   X |
      | X * X |
      | X   X |
      | XXXXX |

  Scenario: The projectile moves left to right
    When the projectile moves right
    Then the projectile is located at (4, 3)

  Scenario: The projectile moves right to left
    When the projectile moves left
    Then the projectile is located at (2, 3)

  Scenario: The projectile moves from top to bottom
    When the projectile moves down
    Then the projectile is located at (3, 4)

  Scenario: The projectile moves bottom to top
    When the projectile moves up
    Then the projectile is located at (3, 2)