package environment;

import exceptions.EnvironmentException;
import exceptions.WeaponException;
import lifeform.LifeForm;

public interface Commands {

  /**
   * reload command
   *
   * @param lf - life form for this command
   */
  void reloadCommand(LifeForm lf);

  /**
   * turn north command
   *
   * @param lf - life form for this command
   */
  void turnNorthCommand(LifeForm lf);

  /**
   * turn south command
   *
   * @param lf - life form for this command
   */
  void turnSouthCommand(LifeForm lf);

  /**
   * turn west command
   *
   * @param lf - life form for this command
   */
  void turnWestCommand(LifeForm lf);

  /**
   * turn east command
   *
   * @param lf - life form for this command
   */
  void turnEastCommand(LifeForm lf);

  /**
   * move command
   *
   * @param lf - life form for this command
   */
  void moveCommand(LifeForm lf);

  /**
   * attack command
   *
   * @param lf - life form for this command
   */
  void attackCommand(LifeForm lf) throws EnvironmentException, WeaponException;

  /**
   * drop command
   *
   * @param lf - life form for this command
   */
  void dropCommand(LifeForm lf);

  /**
   * acquire command
   *
   * @param lf - life form for this command
   */
  void acquireCommand(LifeForm lf);
}