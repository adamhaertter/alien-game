package state;

import environment.Environment;
import lifeform.LifeForm;

/**
 * @author Josh Lewis
 *
 */
public abstract class ActionState {

  protected AiContext context;
  protected Environment env;
  protected LifeForm lifeform;

  /**
   * Sets the actionState
   * 
   * @param ai
   */
  ActionState(AiContext ai) {
    context = ai;
    env = ai.env;
    lifeform = ai.lf;
  }

  /**
   * The template for an action
   */
  public abstract void executeAction();

}
