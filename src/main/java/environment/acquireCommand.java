package environment;

import lifeform.LifeForm;

public class acquireCommand implements InvokerBuilder {
  @Override
  public void execute(LifeForm lf) {
    // TODO Fix eventually
  }

  public void execute(LifeForm lf, Environment env) {
    env.acquireCommand(lf);
  }
}
