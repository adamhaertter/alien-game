package environment;

import lifeform.LifeForm;

/**
 * @author Brennan Mulligan - modified by Scott Bucher
 *
 */
public class AcquireCommand implements Invokers {

  /**
   * Execute the specified command
   *
   * @param lf  - life form for the command
   * @param env - environment for this command
   */
  @Override
  public void execute(LifeForm lf, Environment env) {
    env.acquire(lf);
  }
}
