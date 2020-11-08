package environment;

import lifeform.LifeForm;

public class turnWestCommand implements InvokerBuilder {
  @Override
  public void execute(LifeForm lf, Environment env) {
    env.turnWestCommand(lf);
  }
}