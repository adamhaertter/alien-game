package weapon;

import exceptions.WeaponException;

/**
 * @author Adam Haertter - modified by Evan Paules and Scott Bucher
 *
 */
public class Pistol extends GenericWeapon {

  /**
   * By default, a Pistol deals 10 base damage within a range of 50 feet. It has a
   * rate of fire of 2 and a maximum ammo of 10.
   */
  public Pistol() {
    super(10, 50, 2, 10);
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
        : (int) (this.getBaseDamage() * ((double) ((this.getMaxRange() - distance) 
            + 10) / this.getMaxRange()));
  }

  /**
   * Prints "Pistol"
   */
  public String toString() {
    return "Pistol";
  }
}
