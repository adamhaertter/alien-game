package state;

import environment.Cell;

public class NoWeaponState extends ActionState {

  /**
   * Creates a NoWeaponState
   * @param ai AI
   */
  public NoWeaponState(AIContext ai) {
    super(ai);
  }

  /**
   * Executes NoWeaponState's action
   * and then sets the state to
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
      // search
    }
  }
}
