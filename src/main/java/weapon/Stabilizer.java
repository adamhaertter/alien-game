package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import gameplay.TimerObserver;

/*
 * Stabilizer is an extension of Attachment and uses Weapon and TimerObserver Interfaces.
 * This Modifies a weapons damage and reload speed
 * 
 * @author Evan Paules
 */

public class Stabilizer extends Attachment implements Weapon, TimerObserver {

  public Stabilizer(Weapon baseWeapon) throws AttachmentException {
    if (base.getNumAttachments() < 2) {
      base = baseWeapon;
    }
  }

  /*
   * This method reloads the weapon if the shots left are 0 This method also gives
   * the weapon bonus damage per shot taken
   */
  @Override
  public int fire(int distance) throws WeaponException {
    if (base.getShotsLeft() == 0) {
      base.reload();
    }
    int damage = (int) Math.floor(base.fire(distance) * 1.25);
    return damage;
  }

  @Override
  public String toString() {
    return " + Stabilizer";
  }
}
