package lifeform;

public class Human extends LifeForm {

  private int armor;

  /**
   * Creates an instance of a Human with a name, HP, and armor points
   *
   * @param string the name of the human
   * @param i      the current HP of the human
   * @param arm    the amount of Armor Points the human has
   */
  public Human(String string, int i, int arm) {
    super(string, i);
    if (arm <= 0) {
      armor = 0;
    } else {
      armor = arm;
    }
  }

  /**
   * Deals a given amount of damage to the human
   * 
   * @param damage the amount of damage to be dealt to the Human
   */
  public void takeHit(int damage) {
    super.takeHit(damage);
    // Currently no different from LifeForm's method,
    // but will eventually account for armor
  }

  /**
   * @return the Armor Points held by the Human
   */
  public int getArmorPoints() {
    return armor;
  }

  /**
   * Sets the Armor Points of the Human
   * 
   * @param points The new Armor Points value, must be a positive integer
   */
  public void setArmorPoints(int points) {
    if (points <= 0) {
      armor = 0;
    } else {
      armor = points;
    }
  }

}
