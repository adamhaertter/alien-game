package environment;

import lifeform.LifeForm;

public class turnEastCommand implements InvokerBuilder {
  @Override
  public void execute(LifeForm lf) {
    lf.turn("East");
  }
}