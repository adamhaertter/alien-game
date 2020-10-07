package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import gameplay.TimerObserver;

public class PowerBooster extends Attachment implements Weapon, TimerObserver {
  public PowerBooster(Weapon baseWeapon) throws AttachmentException {
    base = baseWeapon;
  }
  
  public int fire(int distance) throws WeaponException {
    return (base.getBaseDamage() * (1 + (base.getCurrentAmmo() / base.getMaxAmmo())));
  }
  
  public String toString() {
    return " + PowerBooster";
  }
}
