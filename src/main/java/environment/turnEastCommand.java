package environment;

import lifeform.LifeForm;

public class turnEastCommand implements InvokerBuilder {
  @Override
  public void execute(LifeForm lf, Environment env) {
    env.turnEastCommand(lf);
  }
}