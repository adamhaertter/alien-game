package environment;

import lifeform.LifeForm;

public class turnEastCommand implements InvokerBuilder {
  @Override
  public void execute(LifeForm lf) {
    lf.currentDirection = "East";
  }

  @Override
  public void execute() {
    // Nothing is here intentionally ATM
    // Ask Brennan later to either delete or change
  }
}
