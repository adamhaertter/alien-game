package environment;

import lifeform.LifeForm;

/**
 * A Cell that can hold a LifeForm.
 *
 */
public class Cell {

  LifeForm entity;

  /**
   * Constructs an empty cell in which entity is automatically null until a
   * LifeForm is added to it.
   */
  public Cell() {

  }

  /**
   * Tries to add the LifeForm to the Cell. Will not add if a LifeForm is already
   * present
   * 
   * @param lf the LifeForm held in the cell
   * @return true if the LifeForm was added to the Cell, false otherwise.
   */
  public boolean addLifeForm(LifeForm lf) {
    if (entity == null) {
      entity = lf;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Removes the LifeForm present in the Cell by making it null.
   */
  public void removeLifeForm() {
    entity = null;
  }

  /**
   * @return the LifeForm in this Cell.
   */
  public LifeForm getLifeForm() {
    return entity;
  }

}
