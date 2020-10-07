package weapon;

import exceptions.WeaponException;

public abstract class GenericWeapon implements Weapon {

  /**
   * @author Joshua Lewis
   */
  protected int baseDamage;
  protected int currentAmmo;
  protected int maxAmmo;
  protected int maxRange;
  protected int rateOfFire;
  protected int shotsLeft;

  public GenericWeapon() {
  }

  /**
   * @return the damage dealt by the weapon
   */
  public abstract int fire(int distance) throws WeaponException;

  /**
   * @return the base damage of the weapon
   */
  public int getBaseDamage() {
    return baseDamage;
  }

  /**
   * @return the current ammo of the weapon
   */
  public int getCurrentAmmo() {
    return currentAmmo;
  }

  /**
   * @return the max ammo of the weapon
   */
  public int getMaxAmmo() {
    return maxAmmo;
  }

  /**
   * @return the max range of the weapon
   */
  public int getMaxRange() {
    return maxRange;
  }

  /**
   * @return the number of attachments to the weapon
   */
  public int getNumAttachments() {
    return 0;
  }

  /**
   * @return the rate of fire of the weapon
   */
  public int getRateOfFire() {
    return rateOfFire;
  }

  /**
   * @return the shots left in the round for the weapon
   */
  public int getShotsLeft() {
    return shotsLeft;
  }

  /**
   * Sets the current ammo and shots left back to their maximum values
   */
  public void reload() {
    currentAmmo = maxAmmo;
    shotsLeft = rateOfFire;
  }

  public abstract String toString();

  /**
   * On a round update, the shots left in the round are reset to the maximum
   */
  public void updateTime(int time) {
    shotsLeft = rateOfFire;
  }

}
