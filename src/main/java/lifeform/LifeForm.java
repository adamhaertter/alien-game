package lifeform;

/**
 * Keeps track of the information associated with as simple life form. Also
 * provides the functionality related to the life form.
 */
public abstract class LifeForm {

  private String myName;
  protected int currentLifePoints;
  protected int attackStrength;

  /**
   * Creates an instance
   * 
   * @param string the name of the life form
   * @param i      the current starting life points of the life form
   */
  public LifeForm(String string, int i) {
    myName = string;
    currentLifePoints = i;
    attackStrength = 1;
  }

  /**
   * Creates an instance
   * 
   * @param string the name of the life form
   * @param i      the current starting life points of the life form
   * @param atk    the attack strength of the LifeForm
   */
  public LifeForm(String string, int i, int atk) {
    this(string, i);
    attackStrength = atk;
  }

  /**
   * The LifeForm's life points are reduced by the damage taken. If the damage
   * causes life points to drop below zero, they are set to zero.
   * 
   * @param damage the amount of damage to be subtracted from the life points.
   */
  public void takeHit(int damage) {
    int lp = currentLifePoints - damage;
    if (lp < 0) {
      currentLifePoints = 0;
    } else {
      currentLifePoints = lp;
    }
  }

  /**
   * Deals damage equal to the attack strength of this LifeForm to an opposing
   * LifeForm. No damage can be dealt if this LifeForm is dead.
   * 
   * @param opponent the opposing LifeForm that will be taking damage
   */
  public void attack(LifeForm opponent) {
    if (currentLifePoints > 0) {
      opponent.takeHit(attackStrength);
    }
  }

  /**
   * @return the name of the life form
   */
  public String getName() {
    return myName;
  }

  /**
   * @return the amount of current life points the life form has
   */
  public int getCurrentLifePoints() {
    return currentLifePoints;
  }

  /**
   * @return the attack strength possessed by this life form
   */
  public int getAttackStrength() {
    return attackStrength;
  }

}
