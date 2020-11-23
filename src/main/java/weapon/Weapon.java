package weapon;

import exceptions.WeaponException;
import gameplay.TimerObserver;

/**
 * @author Brennan Mulligan - modified by Josh Lewis and Evan Paules
 * 
 */
public interface Weapon extends TimerObserver {

  /**
   * Fires the Weapon according to the Weapon specifications and any modifications.
   * 
   * @param distance the distance away from the target
   * @return the damage dealt by the shot
   * @throws WeaponException
   */
  public int fire(int distance) throws WeaponException;

  /**
   * @return the Weapon's base damage, before any modifications.
   */
  public int getBaseDamage();

  /**
   * @return the remaining ammo in the Weapon.
   */
  public int getCurrentAmmo();

  /**
   * @return the maximum ammo of the Weapon.
   */
  public int getMaxAmmo();

  /**
   * @return the maximum range of the Weapon.
   */
  public int getMaxRange();

  /**
   * @return the number of attachments on the Weapon
   */
  public int getNumAttachments();

  /**
   * @return the Weapon's rate of fire
   */
  public int getRateOfFire();

  /**
   * @return the number of shots left for the Weapon.
   */
  public int getShotsLeft();

  /**
   * Reloads the Weapon accordingly.
   */
  public void reload();

  /**
   * @return the name of the Weapon in String form
   */
  public String toString();

}
