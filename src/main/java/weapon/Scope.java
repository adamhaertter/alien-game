package weapon;

import exceptions.AttachmentException;
import gameplay.TimerObserver;
import exceptions.WeaponException;

/*
 * Scope is an extension of Attachment and uses Weapon and TimerObserver Interfaces.
 * This Modifies a weapons damage and distance
 * 
 * @author Evan Paules
 */
public class Scope extends Attachment implements Weapon, TimerObserver {

  public Scope(Weapon w) throws AttachmentException {
    super(w);
  }

  /*
   * This method adds damage based on the distance of the target
   */
  @Override
  public int fire(int distance) throws WeaponException {
    if (base.getMaxRange() < distance && distance <= getMaxRange()) {
      return base.fire(base.getMaxRange()) + 5;
    } else {
      return base.fire(distance) * (1 + (getMaxRange() - distance) / getMaxRange());
    }
  }

  @Override
  public int getMaxRange() {
    return base.getMaxRange() + 10;
  }

  @Override
  public String toString() {
    return " + Scope";

  }

}
