package state;

import environment.Environment;
import gameplay.TimerObserver;
import lifeform.LifeForm;

public class AIContext implements TimerObserver {
  LifeForm lf;
  Environment env;
  ActionState action;
  DeadState dead;
  HasWeaponState hasWeapon;
  NoWeaponState noWeapon;
  OutOfAmmoState ammo;
  
  public AIContext(LifeForm lifeform, Environment e) {
    lf = lifeform;
    env = e;
  }
  
  public void execute() {
    
  }
  
  public ActionState getCurrentState() {
    return action;
  }
  
  public DeadState getDeadState() {
    return dead;
  }
  
  public Environment getEnvironment() {
    return env;
  }
  
  public HasWeaponState getHasWeaponState() {
    return hasWeapon;
  }
  
  public LifeForm getLifeForm() {
    return lf;
  }
  
  public NoWeaponState getNoWeaponState() {
    return noWeapon;
  }
  
  public OutOfAmmoState getOutOfAmmoState() {
    return ammo;
  }
  
  public void setCurrentState(ActionState newState) {
    action = newState;
  }
  
  @Override
  public void updateTime(int time) {
    // TODO Auto-generated method stub
    
  }
}