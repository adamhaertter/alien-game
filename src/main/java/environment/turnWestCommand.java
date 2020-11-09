package environment;

import lifeform.LifeForm;

public class turnWestCommand implements Invokers {
  @Override
  public void execute(LifeForm lf, Environment env) {
    env.turnWestCommand(lf);
  }
}