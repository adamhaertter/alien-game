package state;

/**
 * @author Josh Lewis
 *
 */
public class DeadState extends ActionState {

  /**
   * Creates a DeadState
   * 
   * @param ai
   */
  DeadState(AiContext ai) {
    super(ai);
  }

  /**
   * Executes DeadState's action Then sets the state to HasWeaponState or
   * NoWeaponState
   */
  public void executeAction() {
    int x;
    int y;
    if (lifeform.hasWeapon()) {
      boolean dropped = false;
      while (!dropped) {
        x = (int) (Math.random() * env.getNumCols());
        y = (int) (Math.random() * env.getNumRows());
        if (env.getCell(x, y).getWeapon1() == null || env.getCell(x, y).getWeapon2() == null) {
          env.addWeapon(lifeform.dropWeapon(), x, y);
          dropped = true;
        }
      }
    }
    boolean placed = false;
    while (!placed) {
      x = (int) (Math.random() * env.getNumCols());
      y = (int) (Math.random() * env.getNumRows());
      if (env.getCell(x, y).getLifeForm() == null) {
        lifeform.revive();
        env.getCell(lifeform.getCol(), lifeform.getRow()).removeLifeForm();
        env.getCell(x, y).addLifeForm(lifeform);
        placed = true;
      }
      if (lifeform.hasWeapon()) {
        context.setCurrentState(context.getHasWeaponState());
      } else {
        context.setCurrentState(context.getNoWeaponState());
      }
    }
  }

}
