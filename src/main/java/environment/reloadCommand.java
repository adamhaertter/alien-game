package environment;

import lifeform.LifeForm;

public class reloadCommand implements InvokerBuilder {
  @Override
  public void execute(LifeForm lf) {
    if (lf.hasWeapon()) {
      lf.weapon.reload();
    }
  }

  @Override
  public void execute() {
    // Nothing is here intentionally ATM
    // Ask Brennan later to either delete or change
  }
}
