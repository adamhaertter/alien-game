package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

/**
 * @author Josh Lewis - modified by Brennan Mulligan, Evan Paules, and Adam
 *         Haertter
 *
 */
public abstract class Attachment extends Object implements Weapon {

  protected Weapon base;

  /**
   * Creates an Attachment and checks that no more than two attachments are
   * already present in the chain.
   * 
   * @param w The wrapped Weapon by the decorator pattern
   * @throws AttachmentException
   */
  public Attachment(Weapon w) throws AttachmentException {
    if (w.getNumAttachments() < 2) {
      this.base = w;
    } else {
      throw new AttachmentException("You already have two attachments");
    }
  }

  /**
   * @return the damage to be dealt by this weapon
   */
  public abstract int fire(int distance) throws WeaponException;

  /**
   * @return the base damage of the weapon
   */
  public int getBaseDamage() {
    return base.getBaseDamage();
  }

  /**
   * @return the current ammo of the weapon
   */
  public int getCurrentAmmo() {
    return base.getCurrentAmmo();
  }

  /**
   * @return the max ammo of the weapon
   */
  public int getMaxAmmo() {
    return base.getMaxAmmo();
  }

  /**
   * @return the max range of the weapon
   */
  public int getMaxRange() {
    return base.getMaxRange();
  }

  /**
   * @return the number of attachments to the weapon
   */
  public int getNumAttachments() {
    return base.getNumAttachments();
  }

  /**
   * @return the rate of fire of the weapon
   */
  public int getRateOfFire() {
    return base.getRateOfFire();
  }

  /**
   * @return the shots left in the round for the weapon
   */
  public int getShotsLeft() {
    return base.getShotsLeft();
  }

  /**
   * Reloads the weapon
   */
  public void reload() {
    base.reload();
  }

  /**
   * Runs updateTime down the Weapon chain
   */
  public void updateTime(int time) {
    base.updateTime(time);
  }

}
