package recovery;

/**
 * @author Adam Haertter
 *
 */
public class RecoveryLinear implements RecoveryBehavior {

  private int linearAmount;

  /**
   * Creates an object that restores its health linearly
   * 
   * @param i the amount by which the LifeForm will heal every time
   */
  public RecoveryLinear(int i) {
    linearAmount = i;
  }

  /**
   * Calculates the health of the LifeForm has restored health. Restores the
   * LifeForm's health by a constant amount every time. Cannot restore health if
   * the LifeForm is already dead. Cannot overheal the LifeForm
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
    int temp = currentLife += linearAmount;
    if (temp > maxLife) {
      temp = maxLife;
    }
    return temp;
  }

}
