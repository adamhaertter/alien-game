package environment;

import lifeform.LifeForm;

public class moveCommand implements Invokers {
  @Override
  public void execute(LifeForm lf, Environment env) {
    env.moveCommand(lf);
  }
}