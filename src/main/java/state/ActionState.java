package state;

import environment.Environment;
import lifeform.LifeForm;

public abstract class ActionState {

  protected AIContext context;
  protected Environment e;
  protected LifeForm lifeform;
  
  /**
   * Sets the actionState
   * @param ai
   */
  ActionState(AIContext ai) {
    context = ai;
    e = ai.env;
    lifeform = ai.lf;
  }
  
  /**
   * The template for an action
   */
  public abstract void executeAction();
  
}
