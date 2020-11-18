package state;

import environment.Environment;
import lifeform.LifeForm;

public abstract class ActionState {

  //protected AIContext context;
  protected Environment e;
  protected LifeForm lifeform;
  
  public ActionState() { //when done add AIContext
    //context = ai;
  }
  
  public abstract void executeAction();
  
}
