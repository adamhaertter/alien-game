package environment;

import lifeform.LifeForm;

public class turnNorthCommand implements Invokers {
  @Override
  public void execute(LifeForm lf, Environment env) {
    env.turnNorthCommand(lf);
  }
}