package state;

import environment.Cell;

import java.util.Random;

/**
 * @author Scott Bucher
 *
 */
public class NoWeaponState extends ActionState {

  /**
   * Creates a NoWeaponState
   * 
   * @param ai AI
   */
  public NoWeaponState(AIContext ai) {
    super(ai);
  }

  /**
   * Executes NoWeaponState's action and then sets the state to
   */
  @Override
  public void executeAction() {
    Cell cell = e.getCell(lifeform.getRow(), lifeform.getCol());
    if (lifeform.getCurrentLifePoints() <= 0) {
      context.setCurrentState(context.getDeadState());
    } else if (cell.getWeapon1() != null || cell.getWeapon2() != null) {
      // There is a weapon in this cell
      if (cell.getWeapon1() != null) {
        lifeform.pickUpWeapon(cell.getWeapon1());
        cell.removeWeapon(cell.getWeapon1());
      } else {
        lifeform.pickUpWeapon(cell.getWeapon2());
        cell.removeWeapon(cell.getWeapon2());
      }
      context.setCurrentState(context.getHasWeaponState());
    } else {
      Random random = new Random(3);
      int direction = random.nextInt() + 1;

      switch (lifeform.currentDirection) {
      case "North":
        if (direction == 1) {
          e.turnSouth(lifeform);
        } else if (direction == 2) {
          e.turnWest(lifeform);
        } else if (direction == 3) {
          e.turnEast(lifeform);
        }
        break;
      case "South":
        if (direction == 1) {
          e.turnNorth(lifeform);
        } else if (direction == 2) {
          e.turnWest(lifeform);
        } else if (direction == 3) {
          e.turnEast(lifeform);
        }

        break;
      case "West":
        if (direction == 1) {
          e.turnNorth(lifeform);
        } else if (direction == 2) {
          e.turnSouth(lifeform);
        } else if (direction == 3) {
          e.turnEast(lifeform);
        }

        break;
      case "East":
        if (direction == 1) {
          e.turnNorth(lifeform);
        } else if (direction == 2) {
          e.turnSouth(lifeform);
        } else if (direction == 3) {
          e.turnWest(lifeform);
        }

        break;
      }

      int move = random.nextInt();
      if (move == 1) {
        e.move(lifeform);
      }
    }
  }
}
