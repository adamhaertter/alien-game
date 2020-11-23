package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import gameplay.TimerObserver;

/*
 * Stabilizer is an extension of Attachment and uses Weapon and TimerObserver Interfaces.
 * This Modifies a weapons damage and reload speed
 * 
 * @author Evan Paules - modified by Brennan Mulligan, Adam Haertter, and Scott Bucher
 */

public class Stabilizer extends Attachment implements Weapon, TimerObserver {

  public Stabilizer(Weapon w) throws AttachmentException {
    super(w);
  }

  /**
   * This method reloads the weapon if the shots left are 0. This method also
   * gives the weapon bonus 25% damage per shot taken
   * 
   * @return the modified damage dealt by the stabilizer.
   */
  @Override
  public int fire(int distance) throws WeaponException {
    int damage = (int) Math.floor(this.base.fire(distance) * 1.25);
    if (this.base.getCurrentAmmo() == 0) {
      this.base.reload();
    }
    return damage;
  }

  /**
   * Prints " +Stabilizer"
   */
  @Override
  public String toString() {
    return base + " +Stabilizer";
  }

  /**
   * @return 1 more than the number of attachments already applied before this
   *         one.
   */
  public int getNumAttachments() {
    return 1 + base.getNumAttachments();
  }
}
