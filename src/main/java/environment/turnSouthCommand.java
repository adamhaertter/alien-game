package environment;

import lifeform.LifeForm;

public class turnSouthCommand implements InvokerBuilder {
  @Override
  public void execute(LifeForm lf) {
    lf.currentDirection = "South";
  }

  @Override
  public void execute() {
    // Nothing is here intentionally ATM
    // Ask Brennan later to either delete or change
  }
}
