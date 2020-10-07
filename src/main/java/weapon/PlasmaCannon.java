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
    currentAmmo = maxAmmo;
    shotsLeft = rateOfFire;
  }

  /**
   * Uses the distance between the user and the target to calculate the damage
   * dealt. No damage is dealt if the target is out of range or if the weapon has
   * no ammo left.
   * 
   * @return the damage dealt by this weapon
   */
  @Override
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("The distance cannot be negative!");
    }
    if (this.getCurrentAmmo() <= 0 || this.getShotsLeft() <= 0) {
      return 0;
    } else {
      shotsLeft--;
      super.currentAmmo--;
    }

    return distance > this.getMaxRange() ? 0
        : (int) (this.getBaseDamage() * ((double)
        (this.getCurrentAmmo() + 1) / this.getMaxAmmo()));
  }

  /**
   * Prints "PlasmaCannon"
   */
  public String toString() {
    return "PlasmaCannon";
  }
}
