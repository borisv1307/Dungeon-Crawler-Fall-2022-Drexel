@IntegrationTest
Feature: Move the enemy into empty space

  Background:
    Given the level design with enemy focus is:
      | XXXXX |
      | X   X |
      | X E X |
      | X   X |
      | XXXXX |

  Scenario: Enemy moves left into empty space
    When the enemy moves left
    Then the enemy is located at (2, 3)

  Scenario: Enemy moves right into empty space
    When the enemy moves right
    Then the enemy is located at (4, 3)

  Scenario: Enemy moves up into empty space
    When the enemy moves up
    Then the enemy is located at (3, 2)

  Scenario: Enemy moves down into empty space
    When the enemy moves down
    Then the enemy is located at (3, 4)
