package lifeform;

import exceptions.RecoveryRateException;
import recovery.RecoveryBehavior;
import recovery.RecoveryNone;

public class Alien extends LifeForm {

  private int recoveryRate;
  private int maxHealth;
  private RecoveryBehavior recoveryBehavior;

  /**
   * Creates an Alien with a name and HP value
   * 
   * @param string the name of the alien
   * @param maxHp  the maximum and current hp of the alien
   */
  public Alien(String string, int maxHp) {
    super(string, maxHp);
    recoveryRate = 0;
    attackStrength = 10;
    maxHealth = maxHp;
    recoveryBehavior = new RecoveryNone();
  }

  /**
   * Creates an Alien with a name, HP, and RecoveryBehavior pattern
   * 
   * @param string   the name of the alien
   * @param maxHp    the maximum and current hp of the alien
   * @param behavior the type of RecoveryBehavior to be used by the alien
   */
  public Alien(String string, int maxHp, RecoveryBehavior behavior) {
    this(string, maxHp);
    recoveryBehavior = behavior;
  }

  /**
   * Creates an Alien with a name, HP, RecoveryBehavior pattern, and recovery rate
   * 
   * @param string   the name of the alien
   * @param maxHp    the maximum and current hp of the alien
   * @param behavior the type of RecoveryBehavior to be used by the alien
   * @param rate     the HP recovery rate of the alien
   */
  public Alien(String string, int maxHp, RecoveryBehavior behavior, int rate) 
      throws RecoveryRateException {
    this(string, maxHp, behavior);
    recoveryRate = rate;
    if (recoveryRate < 0) {
      throw new RecoveryRateException("Recovery Rate is less than 0");
    }
  }

  /**
   * @return the maximum health the Alien can have
   */
  public int getMaxLifePoints() {
    return maxHealth;
  }

  /**
   * @return the recovery rate of the Alien
   */
  public int getRecoveryRate() {
    return recoveryRate;
  }

  /**
   * Restores the Alien's HP in accordance with its Recovery Behavior and Rate
   */
  protected void recover() {
    currentLifePoints = recoveryBehavior.calculateRecovery(currentLifePoints, maxHealth);
  }

}
