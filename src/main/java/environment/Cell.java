package environment;

import lifeform.LifeForm;
import weapon.Weapon;

/**
 * A Cell that can hold a LifeForm.
 *
 */
public class Cell {

  LifeForm entity;
  Weapon wOne;
  Weapon wTwo;

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
  
  public boolean addWeapon(Weapon weapon) {
    if (wOne == null) {
      wOne = weapon;
      return true;
    } else if(wTwo == null && wOne != weapon) {
      wTwo = weapon;
      return true;
    }
    return false;
  }
  
  public Weapon getWeapon1() {
    return wOne;
  }
  
  public Weapon getWeapon2() {
    return wTwo;
  }
  
  public int getWeaponsCount() {
    int count = 0;
    if (wOne != null) {
      count++;
    }
    if (wTwo != null) {
      count++;
    }
    return count;
  }
  
  public Weapon removeWeapon(Weapon weapon) {
    if (wOne == weapon) {
      wOne = null;
      return weapon;
    }
    if (wTwo == weapon) {
      wTwo = null;
      return weapon;
    }
    return null;
  }
}
