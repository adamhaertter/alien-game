package recovery;

/**
 * @author Adam Haertter
 *
 */
public class RecoveryNone implements RecoveryBehavior {

  /**
   * Calculates the health of the LifeForm has restored health. Does not restore
   * the LifeForm's health. Simply returns the current life points held by the
   * LifeForm because these LifeForms are incapable of healing
   * 
   * @param currentLife The LifeForm's current life points
   * @param maxLife     The maximum life points the LifeForm can have
   * @return the LifeForm's health after healing (NOT the amount healed)
   */
  @Override
  public int calculateRecovery(int currentLife, int maxLife) {
    // No calculations are necessary because no health will be recovered.
    return currentLife;
  }

}
