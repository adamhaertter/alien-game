package weapon;

import exceptions.WeaponException;

/**
 * @author Adam Haertter - modified by Evan Paules and Scott Bucher
 *
 */
public class ChainGun extends GenericWeapon {

  /**
   * By default, a Chain Gun deals 15 base damage within a range of 60 feet. It
   * has a rate of fire of 4 and a maximum ammo of 40.
   */
  public ChainGun() {
    super(15, 60, 4, 40);
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
    if (currentAmmo <= 0 || shotsLeft <= 0) {
      return 0;
    }

    float ret = 0.0f;
    ret = distance;
    ret /= maxRange;
    ret *= baseDamage;
    currentAmmo--;
    shotsLeft--;

    if (distance > maxRange) {
      return 0;
    }
    return (int) Math.floor(ret);
  }

  /**
   * Prints "ChainGun"
   */
  public String toString() {
    return "ChainGun";
  }
}
