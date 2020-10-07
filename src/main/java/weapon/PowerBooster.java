package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import gameplay.TimerObserver;

/**
 * PowerBooster is an extension of Attachment and uses Weapon and TimerObserver
 * Interfaces. This modifies the weapon's damage depending on the amount of ammo
 * it has left.
 * 
 * @author Brennan Mulligan
 */
public class PowerBooster extends Attachment implements Weapon, TimerObserver {

  public PowerBooster(Weapon w) throws AttachmentException {
    super(w);
  }

  public int fire(int distance) throws WeaponException {
    return ((1 + (getCurrentAmmo() / getMaxAmmo())) * base.fire(distance));
  }

  public String toString() {
    return " + PowerBooster";
  }
}
