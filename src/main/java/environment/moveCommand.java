package environment;

import lifeform.LifeForm;

public class moveCommand implements InvokerBuilder {
  @Override
  public void execute(LifeForm lf) {

  }

  public void execute(LifeForm lf, Environment env) {
    env.moveCommand(lf);
  }
}