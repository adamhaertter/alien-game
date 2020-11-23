package state;

import environment.Environment;
import gameplay.TimerObserver;
import lifeform.LifeForm;

/**
 * @author Brennan Mulligan - edited by Adam Haertter
 *
 */
public class AIContext implements TimerObserver {
  LifeForm lf;
  Environment env;
  ActionState action;
  DeadState dead;
  HasWeaponState hasWeapon;
  NoWeaponState noWeapon;
  OutOfAmmoState ammo;
  public static int myTime;

  /**
   * Creates an instance of AIConext from the specified values.
   * 
   * @param lifeform the LifeForm to be wrapped by the AIContext
   * @param e        the environment for the changes to occur.
   */
  public AIContext(LifeForm lifeform, Environment e) {
    lf = lifeform;
    env = e;
  }

  /**
   * Template implementation of execute
   */
  public void execute() {

  }

  /**
   * @return the current ActionState of the AIContext.
   */
  public ActionState getCurrentState() {
    return action;
  }

  /**
   * @return the DeadState of the AIContext, regardless of whether it is active.
   */
  public DeadState getDeadState() {
    return dead;
  }

  /**
   * @return the Environment that the AIContext resides in.
   */
  public Environment getEnvironment() {
    return env;
  }

  /**
   * @return the HasWeaponState of the AIContext, regardless of whether it is
   *         active.
   */
  public HasWeaponState getHasWeaponState() {
    return hasWeapon;
  }

  /**
   * @return the LifeForm wrapped by the AIContext.
   */
  public LifeForm getLifeForm() {
    return lf;
  }

  /**
   * @return the NoWeaponState of the AIContext, regardless of whether it is
   *         active.
   */
  public NoWeaponState getNoWeaponState() {
    return noWeapon;
  }

  /**
   * @return the OutOfAmmoState of the AIContext, regardless of whether it is
   *         active.
   */
  public OutOfAmmoState getOutOfAmmoState() {
    return ammo;
  }

  /**
   * Sets the current ActionState of the AIContext to the specified value.
   * 
   * @param newState the new state for the AIContext to inherit.
   */
  public void setCurrentState(ActionState newState) {
    action = newState;
  }

  /**
   * Sets the global monitored time to the current round.
   */
  @Override
  public void updateTime(int time) {
    // TODO Auto-generated method stub
    myTime = time;
  }
}