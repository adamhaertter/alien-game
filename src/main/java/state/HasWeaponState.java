package state;

import environment.Cell;
import exceptions.EnvironmentException;
import lifeform.LifeForm;

import java.util.Random;

/**
 * @author Scott Bucher
 *
 */
public class HasWeaponState extends ActionState {

  /**
   * Creates a HasWeapon State
   * 
   * @param ai AI
   */
  public HasWeaponState(AiContext ai) {
    super((ai));
  }

  /**
   * Execute the HasWeaponState's action and then sets the state to
   */
  @Override
  public void executeAction() {
    int x;
    int y;

    LifeForm target = null;
    int distance = lifeform.getWeapon().getMaxRange();
    // find if there is a target or not
    if (lifeform.currentDirection.equalsIgnoreCase("north")) {
      for (int i = 1; i <= distance; i++) {

        if (lifeform.getRow() - i >= 0) {
          target = env.getCell(lifeform.getRow() - i, lifeform.getCol()).getLifeForm();
          if (target != null) {
            i = distance + 1;
          }
        } else {
          i = distance + 1;
        }
      }
    } else if (lifeform.currentDirection.equalsIgnoreCase("south")) {
      for (int i = 1; i <= distance; i++) {

        if (lifeform.getRow() + i < env.cells.length) {
          target = env.getCell(lifeform.getRow() + i, lifeform.getCol()).getLifeForm();
          if (target != null) {
            i = distance + 1;
          }
        } else {
          i = distance + 1;
        }
      }
    } else if (lifeform.currentDirection.equalsIgnoreCase("east")) {
      for (int i = 1; i <= distance; i++) {

        if (lifeform.getCol() + i < env.cells[0].length) {
          target = env.getCell(lifeform.getRow(), lifeform.getCol() + i).getLifeForm();
          if (target != null) {
            i = distance + 1;
          }
        } else {
          i = distance + 1;
        }
      }
    } else if (lifeform.currentDirection.equalsIgnoreCase("west")) {
      for (int i = 1; i <= distance; i++) {

        if (lifeform.getCol() - i >= 0) {
          target = env.getCell(lifeform.getRow(), lifeform.getCol() - i).getLifeForm();
          if (target != null) {
            i = distance + 1;
          }
        } else {
          i = distance + 1;
        }
      }
    }

    if (target != null) {
      if (lifeform.getWeapon().getCurrentAmmo() <= 0) {
        context.setCurrentState(context.getOutOfAmmoState());
      } else {
        try {
          lifeform.attack(target, (int) env.getDistance(lifeform, target));
        } catch (EnvironmentException environmentException) {
          environmentException.printStackTrace();
        }
      }
    } else {
      Random random = new Random(3);
      int direction = random.nextInt() + 1;

      switch (lifeform.currentDirection) {
        case "North":
          if (direction == 1) {
            env.turnSouth(lifeform);
          } else if (direction == 2) {
            env.turnWest(lifeform);
          } else if (direction == 3) {
            env.turnEast(lifeform);
          }
          break;
        case "South":
          if (direction == 1) {
            env.turnNorth(lifeform);
          } else if (direction == 2) {
            env.turnWest(lifeform);
          } else if (direction == 3) {
            env.turnEast(lifeform);
          }

          break;
        case "West":
          if (direction == 1) {
            env.turnNorth(lifeform);
          } else if (direction == 2) {
            env.turnSouth(lifeform);
          } else if (direction == 3) {
            env.turnEast(lifeform);
          }

          break;
        case "East":
          if (direction == 1) {
            env.turnNorth(lifeform);
          } else if (direction == 2) {
            env.turnSouth(lifeform);
          } else if (direction == 3) {
            env.turnWest(lifeform);
          }

          break;
        default:
          break;
      }

      int move = random.nextInt();
      if (move == 1) {
        env.move(lifeform);
      }
    }

  }
}
