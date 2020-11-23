package lifeform;

/**
 * @author Adam Haertter - modified by Scott Bucher
 *
 */
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
    attackStrength = 5;
    maxSpeed = 3;
  }

  /**
   * Deals a given amount of damage to the human. An initial amount of damage will
   * be absorbed by the armor.
   * 
   * @param damage the amount of damage to be dealt to the Human
   */
  public void takeHit(int damage) {
    int damageBuffer = armor - damage;
    if (damageBuffer < 0) {
      super.takeHit(Math.abs(damageBuffer));
    }
    // If armor - damage is 0 or greater, all impact damage will be absorbed by
    // armor, dealing no damage to the Human.
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

  /**
   * Prints "Human"
   */
  public String toString() {
    return "Human";
  }
}
