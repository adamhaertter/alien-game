package environment;

import lifeform.LifeForm;

public class dropCommand implements Invokers {
  @Override
  public void execute(LifeForm lf, Environment env) {
    env.dropCommand(lf);
  }
}
