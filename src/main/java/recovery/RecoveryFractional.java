package recovery;

public class RecoveryFractional implements RecoveryBehavior {

  double fractionalAmount;

  /**
   * Creates an object which restores its health fractionally. Takes in a fraction
   * which must be between 0.0 and 1.0
   * 
   * @param fraction the fraction of max HP that the LifeForm will heal with
   */
  public RecoveryFractional(double fraction) {
    fractionalAmount = fraction;
    if (fraction < 0) {
      fractionalAmount = 0;
    }
    if (fraction > 1) {
      fractionalAmount = 1;
    }
  }

  /**
   * Calculates the health of the LifeForm has restored health. Restores the
   * LifeForm's health by a fraction of its current HP. Cannot restore health if the
   * LifeForm is already dead. Cannot overheal the LifeForm
   * 
   * @param currentLife The LifeForm's current life points
   * @param maxLife     The maximum life points the LifeForm can have
   * @return the LifeForm's health after healing (NOT the amount healed)
   */
  @Override
  public int calculateRecovery(int currentLife, int maxLife) {
    if (currentLife <= 0) {
      return 0;
    }
    int temp = currentLife + (int)Math.ceil(fractionalAmount * currentLife);
    if (temp > maxLife) {
      temp = maxLife;
    }
    return temp;
  }

}
