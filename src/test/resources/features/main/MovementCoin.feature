@IntegrationTest
Feature: Move the player over a coin and collect it

  Background:
    Given the level design is:
      | XXXXX |
      | X C X |
      | X P X |
      | X   X |
      | XXXXX |

  Scenario Outline: Moves onto coin with <currentCoins> coins
    Given the player starts with <currentCoins> coins
    And the player is at level <currentLevel>
    When the player moves up
    Then the player is located at (<xCoord>, <yCoord>)
    And the player has <newCoins> coins
    And the player is at level <newLevel>
    And the player is color <levelColor>


    Examples:
      | currentCoins | currentLevel | xCoord | yCoord | newCoins | newLevel | levelColor |
      | 0            | 1            | 3      | 2      | 1        | 1        | "GREEN"    |
      | 4            | 1            | 3      | 2      | 0        | 2        | "BLUE"     |
      | 9            | 2            | 3      | 2      | 0        | 3        | "MAGENTA"  |
