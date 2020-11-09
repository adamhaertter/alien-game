package environment;

import lifeform.LifeForm;

public class turnEastCommand implements Invokers {
  @Override
  public void execute(LifeForm lf, Environment env) {
    env.turnEastCommand(lf);
  }
}