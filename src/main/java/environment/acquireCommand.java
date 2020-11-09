package environment;

import lifeform.LifeForm;

public class acquireCommand implements Invokers {
  @Override
  public void execute(LifeForm lf, Environment env) {
    env.acquireCommand(lf);
  }
}
