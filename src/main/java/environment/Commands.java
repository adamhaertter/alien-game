package environment;

import lifeform.LifeForm;

public interface Commands {

  void reloadCommand(LifeForm lf);

  void turnNorthCommand(LifeForm lf);

  void turnSouthCommand(LifeForm lf);

  void turnWestCommand(LifeForm lf);

  void turnEastCommand(LifeForm lf);

  void moveCommand(LifeForm lf);

  void attackCommand(LifeForm lf);

  void dropCommand(LifeForm lf);

  void acquireCommand(LifeForm lf);


}
