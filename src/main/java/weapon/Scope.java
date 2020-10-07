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

  /**
   * This method adds damage based on the distance of the target.
   * 
   * @return the modified damage dealt by the scope
   */
  @Override
  public int fire(int distance) throws WeaponException {
    if (base.getMaxRange() < distance && distance <= getMaxRange()) {
      return base.fire(base.getMaxRange()) + 5;
    } else {
      float ret = 0.0f;
      ret = this.getMaxRange() - distance;
      ret /= this.getMaxRange();
      ret++;
      ret *= base.fire(distance);

      return (int) Math.floor(ret);
    }
  }

  /**
   * @return 10 feet over the old max range
   */
  @Override
  public int getMaxRange() {
    return base.getMaxRange() + 10;
  }

  /**
   * Prints " +Scope"
   */
  @Override
  public String toString() {
    return base + " +Scope";

  }

  /**
   * @return 1 more than the number of attachments already applied before this
   *         one.
   */
  public int getNumAttachments() {
    return 1 + base.getNumAttachments();
  }
}
