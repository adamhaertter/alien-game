package state;

import environment.Environment;
import lifeform.LifeForm;

public class OutOfAmmoState extends ActionState {
  
  OutOfAmmoState() { //when done add AIContext
    //context = ai;
  }

  public void executeAction() {
    if(lifeform.getCurrentLifePoints() <= 0) {
      //context.setCurrentState(new DeadState)
    } else {
      lifeform.getWeapon().reload();
    }
    
  }
}
