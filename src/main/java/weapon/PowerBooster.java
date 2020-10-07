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
  public PowerBooster(Weapon baseWeapon) throws AttachmentException {
    if (base.getNumAttachments() < 2) {
      base = baseWeapon;
    } else {
      throw new AttachmentException("You already have two attachments");
    }
  }

  public int fire(int distance) throws WeaponException {
    return (base.getBaseDamage() * (1 + (base.getCurrentAmmo() / base.getMaxAmmo())));
  }

  public String toString() {
    return " + PowerBooster";
  }
}
