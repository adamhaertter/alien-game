package state;

import environment.Cell;
import exceptions.EnvironmentException;
import lifeform.LifeForm;

import java.util.Random;

public class HasWeaponState extends ActionState {

  /**
   * Creates a HasWeapon State
   * @param ai AI
   */
  public HasWeaponState(AIContext ai) {
    super((ai));
  }

  /**
   * Execute the HasWeaponState's action
   * and then sets the state to
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
          target = e.getCell(lifeform.getRow() - i, lifeform.getCol()).getLifeForm();
          if (target != null) {
            i = distance + 1;
          }
        } else {
          i = distance + 1;
        }
      }
    } else if (lifeform.currentDirection.equalsIgnoreCase("south")) {
      for (int i = 1; i <= distance; i++) {

        if (lifeform.getRow() + i < e.cells.length) {
          target = e.getCell(lifeform.getRow() + i, lifeform.getCol()).getLifeForm();
          if (target != null) {
            i = distance + 1;
          }
        } else {
          i = distance + 1;
        }
      }
    } else if (lifeform.currentDirection.equalsIgnoreCase("east")) {
      for (int i = 1; i <= distance; i++) {

        if (lifeform.getCol() + i < e.cells[0].length) {
          target = e.getCell(lifeform.getRow(), lifeform.getCol() + i).getLifeForm();
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
          target = e.getCell(lifeform.getRow(), lifeform.getCol() - i).getLifeForm();
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
          lifeform.attack(target, (int) e.getDistance(lifeform, target));
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
