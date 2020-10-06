package weapon;

import exceptions.WeaponException;

/**
 * @author Adam Haertter
 *
 */
public class ChainGun extends GenericWeapon {

  /**
   * By default, a Chain Gun deals 15 base damage within a range of 60 feet.
   * It has a rate of fire of 4 and a maximum ammo of 40.
   */
  public ChainGun() {
    baseDamage = 15;
    maxRange = 60;
    rateOfFire = 4;
    maxAmmo = 40;
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
    ret = distance / maxRange;
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
    return "Chain Gun";
  }
}
