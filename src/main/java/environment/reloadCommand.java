package environment;

import lifeform.LifeForm;

public class reloadCommand implements InvokerBuilder {
  @Override
  public void execute(LifeForm lf, Environment env) {
    env.reloadCommand(lf);
  }
}