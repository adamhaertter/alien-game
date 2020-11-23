package weapon;

import exceptions.AttachmentException;
import gameplay.TimerObserver;
import exceptions.WeaponException;

/*
 * Scope is an extension of Attachment and uses Weapon and TimerObserver Interfaces.
 * This Modifies a weapons damage and distance
 * 
 * @author Evan Paules - modified by Brennan Mulligan, Adam Haertter, Scott Bucher
 */
public class Scope extends Attachment implements Weapon, TimerObserver {

  /**
   * The Scope extends the range of the Weapon.
   * 
   * @param w the Weapon to be wrapped by the attachment.
   * @throws AttachmentException
   */
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
    if (this.base.getMaxRange() < distance && distance <= this.getMaxRange()) {
      return 5 + this.base.fire(this.base.getMaxRange());
    } else {
      return (int) (this.base.fire(distance) * (1 + (double) (this.getMaxRange() - distance) / this.getMaxRange()));
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
