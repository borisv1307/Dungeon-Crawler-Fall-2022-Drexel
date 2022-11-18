@Adhoc
Feature: Move the Projectile in empty Space

  Background:
    Given the level design is:
      | XXXXX |
      | X*  X |
      | X   X |
      | X   X |
      | XXXXX |

  Scenario: The projectile moves right to left
    When the projectile moves right
    Then the projectile is located at (3, 2)