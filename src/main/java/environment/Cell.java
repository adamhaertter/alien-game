package environment;

import lifeform.LifeForm;
import weapon.Weapon;

/**
 * A Cell that can hold a LifeForm.
 *
 */
public class Cell {

  LifeForm entity;
  Weapon wepOne;
  Weapon wepTwo;
  int row = -1;
  int col = -1;

  /**
   * Constructs an empty cell in which entity is automatically null until a
   * LifeForm is added to it.
   */
  public Cell() {

  }

  /**
   * Sets the current monitored location of the cell
   * @param row the row index
   * @param col the col index
   */
  public void setLocation(int row, int col) {
    if(row > -1 && col > -1) {
      this.row = row;
      this.col = col;
    }
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
      entity.setLocation(row, col);
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

  /**
   * adds weapon to the cell
   * 
   * @param weapon
   * @return true/false, if weapon was added or not
   */
  public boolean addWeapon(Weapon weapon) {
    if (wepOne == null) {
      wepOne = weapon;
      return true;
    } else if (wepTwo == null && wepOne != weapon) {
      wepTwo = weapon;
      return true;
    }
    return false;
  }

  /**
   * @return Weapon one
   */
  public Weapon getWeapon1() {
    return wepOne;
  }

  /**
   * @return Weapon two
   */
  public Weapon getWeapon2() {
    return wepTwo;
  }

  /**
   * Gets weapons count
   * 
   * @return The weapon count
   */
  public int getWeaponsCount() {
    int count = 0;
    if (wepOne != null) {
      count++;
    }
    if (wepTwo != null) {
      count++;
    }
    return count;
  }

  /**
   * Removes a weapon from a cell
   * 
   * @param weapon
   * @return The weapon that was removed
   */
  public Weapon removeWeapon(Weapon weapon) {
    if (wepOne == weapon) {
      wepOne = null;
      return weapon;
    }
    if (wepTwo == weapon) {
      wepTwo = null;
      return weapon;
    }
    return null;
  }
}
