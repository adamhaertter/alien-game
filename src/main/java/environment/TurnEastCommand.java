package environment;

import lifeform.LifeForm;

public class TurnEastCommand implements Invokers {

  /**
   * Execute the specified command
   *
   * @param lf - life form for the command
   * @param env - environment for this command
   */
  @Override
  public void execute(LifeForm lf, Environment env) {
    env.turnEast(lf);
  }
}