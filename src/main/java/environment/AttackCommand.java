package environment;

import exceptions.EnvironmentException;
import exceptions.WeaponException;
import lifeform.LifeForm;

public class AttackCommand implements Invokers {

  /**
   * Execute the specified command
   *
   * @param lf - life form for the command
   * @param env - environment for this command
   */
  @Override
  public void execute(LifeForm lf, Environment env) {
    try {
      env.attack(lf);
    } catch (EnvironmentException | WeaponException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}