package environment;

import lifeform.LifeForm;

public class reloadCommand implements InvokerBuilder {
  @Override
  public void execute(LifeForm lf) {
    if (lf.hasWeapon()) {
      lf.weapon.reload();
    }
  }
}