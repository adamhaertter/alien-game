package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import gameplay.TimerObserver;

/**
 * PowerBooster is an extension of Attachment and uses Weapon and TimerObserver
 * Interfaces. This modifies the weapon's damage depending on the amount of ammo
 * it has left.
 * 
 * @author Brennan Mulligan - modified by Adam Haertter and Scott Bucher
 */
public class PowerBooster extends Attachment implements Weapon, TimerObserver {

  /**
   * The Power Booster amplifies the damage dealt based on how much ammo is left.
   * 
   * @param w the wrapped weapon by the decorator pattern
   * @throws AttachmentException
   */
  public PowerBooster(Weapon w) throws AttachmentException {
    super(w);
  }

  /**
   * Amplifies the damage dealt by a shot by the proportion of ammo remaining.
   * 
   * @return the modified damage dealt by the booster.
   */
  @Override
  public int fire(int distance) throws WeaponException {
    return Double
        .valueOf(
            Math.floor((1 + ((double) this.base.getCurrentAmmo() / this.base.getMaxAmmo())) * this.base.fire(distance)))
        .intValue();
  }

  /**
   * Prints " +PowerBooster"
   */
  public String toString() {
    return base + " +PowerBooster";
  }

  /**
   * @return 1 more than the number of attachments already applied before this
   *         one.
   */
  public int getNumAttachments() {
    return 1 + base.getNumAttachments();
  }
}
