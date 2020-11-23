package lifeform;

import exceptions.RecoveryRateException;
import exceptions.WeaponException;
import gameplay.TimerObserver;
import recovery.RecoveryBehavior;
import recovery.RecoveryNone;

public class Alien extends LifeForm implements TimerObserver {

  private int recoveryRate;
  private int maxHealth;
  private RecoveryBehavior recoveryBehavior;
  private int currentRound;

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
    maxSpeed = 2;
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
    maxSpeed = 2;
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
  public Alien(String string, int maxHp, RecoveryBehavior behavior, int rate) throws RecoveryRateException {
    this(string, maxHp, behavior);
    recoveryRate = rate;
    maxSpeed = 2;
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
   * @return the current round of the timer the Alien is observing.
   */
  public int getCurrentRound() {
    return currentRound;
  }

  /**
   * Restores the Alien's HP in accordance with its Recovery Behavior and Rate
   */
  protected void recover() {
    currentLifePoints = recoveryBehavior.calculateRecovery(currentLifePoints, maxHealth);
  }

  /**
   * Updates the time using the passed round and executes any necessary processes
   * 
   * @param time the current round number
   */
  @Override
  public void updateTime(int time) {
    currentRound = time;
    if (recoveryRate != 0 && time % recoveryRate == 0) {
      recover();
    }
  }

  /**
   * Prints "Alien"
   */
  public String toString() {
    return "Alien";
  }

}
