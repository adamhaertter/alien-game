package recovery;

public interface RecoveryBehavior {

  /**
   * Calculates the health of the LifeForm has restored health
   * 
   * @param currentLife The LifeForm's current life points
   * @param maxLife     The maximum life points the LifeForm can have
   * @return the LifeForm's health after healing (NOT the amount healed)
   */
  public int calculateRecovery(int currentLife, int maxLife);

}
