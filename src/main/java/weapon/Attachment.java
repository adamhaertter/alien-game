package weapon;

public abstract class Attachment extends Object implements Weapon {
  
  /**
   * @author Joshua Lewis
   */
  protected Weapon base;
  
  public Attachment() { }
  
  public abstract int fire(int distance);
  
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
  
  public void updateTime() {
    base.getShotsLeft();
  }
  
}
