package state;

import environment.Environment;
import lifeform.LifeForm;

/**
 * @author Josh Lewis
 *
 */
public class OutOfAmmoState extends ActionState {

  /**
   * Constructs an OutOfAmmoState
   * 
   * @param ai
   */
  OutOfAmmoState(AIContext ai) {
    super(ai);
  }

  /**
   * Executes the OutOfAmmo Action Then sets state to HasWeaponState
   */
  public void executeAction() {
    if (lifeform.getCurrentLifePoints() <= 0) {
      context.setCurrentState(context.getDeadState());
    } else {
      lifeform.getWeapon().reload();
      context.setCurrentState(context.getHasWeaponState());
    }

  }
}
