package lifeform;

/**
 * Keeps track of the information associated with as simple life form. Also
 * provides the functionality related to the life form.
 */
public class LifeForm {

  String myName;
  int currentLifePoints;

  /**
   * Creates an instance
   * 
   * @param string the name of the life form
   * @param i      the current starting life points of the life form
   */
  public LifeForm(String string, int i) {
    myName = string;
    currentLifePoints = i;
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

}
