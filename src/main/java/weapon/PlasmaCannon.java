package weapon;

import exceptions.WeaponException;

/**
 * @author Adam Haertter
 *
 */
public class PlasmaCannon extends GenericWeapon {

  /**
   * By default, a Plasma Cannon deals 50 base damage within a range of 40 feet.
   * It has a rate of fire of 1 and a maximum ammo of 4.
   */
  public PlasmaCannon() {
    baseDamage = 50;
    maxRange = 40;
    rateOfFire = 1;
    maxAmmo = 4;
  }

  /**
   * Uses the distance between the user and the target to calculate the damage
   * dealt. No damage is dealt if the target is out of range or if the weapon has
   * no ammo left.
   * 
   * @return the damage dealt by this weapon
   */
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Negative distance");
    }
    if (currentAmmo <= 0) {
      currentAmmo = 0;
      return 0;
    }

    float ret = 0.0f;
    ret = currentAmmo / maxAmmo;
    ret *= baseDamage;
    currentAmmo--;

    if (distance > maxRange) {
      return 0;
    }
    return Math.round(ret);
  }

  /**
   *  @TODO: Implement specific toString printable
   */
  public String toString() {
    return null;
  }
}
