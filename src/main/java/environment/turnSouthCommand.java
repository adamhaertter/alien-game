package environment;

import lifeform.LifeForm;

public class turnSouthCommand implements InvokerBuilder {
  @Override
  public void execute(LifeForm lf, Environment env) {
    env.turnSouthCommand(lf);
  }
}