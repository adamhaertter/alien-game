package weapon;

import exceptions.WeaponException;

public abstract class Attachment extends Object implements Weapon {

  /**
   * @author Joshua Lewis
   */
  protected Weapon base;

  public Attachment() {
  }

  public abstract int fire(int distance) throws WeaponException;

  public int getBaseDamage() {
    return base.getBaseDamage();
  }

  public int getCurrentAmmo() {
    return base.getCurrentAmmo();
  }

  public int getMaxAmmo() {
    return base.getMaxAmmo();
  }

  public int getMaxRange() {
    return base.getMaxRange();
  }

  public int getNumAttachments() {
    return base.getNumAttachments();
  }

  public int getRateOfFire() {
    return base.getRateOfFire();
  }

  public int getShotsLeft() {
    return base.getShotsLeft();
  }

  public void reload() {
    base.reload();
  }

  public void updateTime(int time) {
    base.reload();
  }

}
