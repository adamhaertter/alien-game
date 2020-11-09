package environment;

import exceptions.EnvironmentException;
import exceptions.WeaponException;
import lifeform.LifeForm;

public class attackCommand implements Invokers {
  @Override
  public void execute(LifeForm lf, Environment env) {
    try {
      env.attackCommand(lf);
    } catch (EnvironmentException | WeaponException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}