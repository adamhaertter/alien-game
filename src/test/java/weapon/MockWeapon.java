package weapon;

import exceptions.WeaponException;

/**
 * @author Adam Haertter
 *
 */
public class MockWeapon extends GenericWeapon {

  public MockWeapon() {
    maxAmmo = 10;
    baseDamage = 10;
    rateOfFire = 10;
    maxRange = 10;
    currentAmmo = maxAmmo;
    shotsLeft = rateOfFire;
  }

  /**
   * Calculates the damage to be dealt to the enemy when the weapon is fired. No
   * damage is dealt if no ammo is present or the target is out of range. Contains
   * the basic framework that should be done by any Weapon's fire method.
   * 
   * @return the damage dealt by the weapon
   */
  @Override
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Negative distance");
    }
    if (currentAmmo <= 0 || shotsLeft <= 0) {
      return 0;
    }

    // Arithmetic goes here
    currentAmmo--;
    shotsLeft--;

    if (distance > maxRange) {
      return 0;
    }
    return getBaseDamage();
  }

  /**
   * Prints "GenericWeapon", although this method is likely to never be used.
   */
  @Override
  public String toString() {
    return "GenericWeapon";
  }

}