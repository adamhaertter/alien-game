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

  public Scope(Weapon base) throws AttachmentException {
    if (base.getNumAttachments() < 2) {
      Scope one = new Scope(base);
      one.getMaxRange();
      this.base = base;
    }
  }

  /*
   * This method adds damage based on the distance of the target
   */
  @Override
  public int fire(int distance) throws WeaponException {

    if (base.getMaxRange() < distance && distance <= base.getMaxRange() + 10) {
      return base.getBaseDamage() + 5;
    } else
      return base.getBaseDamage() * (1 + (base.getBaseDamage() + 5 - distance) / base.getBaseDamage() + 5);

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
