package environment;

import exceptions.EnvironmentException;
import exceptions.WeaponException;
import lifeform.LifeForm;

public interface Commands {

  void reloadCommand(LifeForm lf);

  void turnNorthCommand(LifeForm lf);

  void turnSouthCommand(LifeForm lf);

  void turnWestCommand(LifeForm lf);

  void turnEastCommand(LifeForm lf);

  void moveCommand(LifeForm lf);

  void attackCommand(LifeForm lf) throws EnvironmentException, WeaponException;

  void dropCommand(LifeForm lf);

  void acquireCommand(LifeForm lf);
}