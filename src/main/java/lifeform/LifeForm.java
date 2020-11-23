package lifeform;

import java.awt.image.BufferedImage;

import exceptions.WeaponException;
import weapon.Weapon;

/**
 * Keeps track of the information associated with as simple life form. Also
 * provides the functionality related to the life form.
 * 
 * @author Adam Haertter - modified by Brennan Mulligan and Scott Bucher
 */
public abstract class LifeForm {

  private String myName;
  protected int currentLifePoints;
  protected int maxLifePoints;
  protected int attackStrength;
  public Weapon weapon;
  protected int row;
  protected int col;
  public String currentDirection = "North";
  public int maxSpeed;

  /**
   * Creates an instance
   * 
   * @param string the name of the life form
   * @param i      the current starting life points of the life form
   */
  public LifeForm(String string, int i) {
    myName = string;
    currentLifePoints = i;
    maxLifePoints = i;
    attackStrength = 1;
    row = -1;
    col = -1;
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
    row = -1;
    col = -1;
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
   * @param distance the distance between the two life forms
   */
  public void attack(LifeForm opponent, int distance) {
    if (currentLifePoints > 0 && !((this.toString()).equals(opponent.toString()))) {
      if (weapon != null && weapon.getCurrentAmmo() != 0) {
        try {
          opponent.takeHit(weapon.fire(distance));
        } catch (WeaponException e) {
          e.printStackTrace();
        }
      } else if (distance <= 5) {
        opponent.takeHit(attackStrength);
      }
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
   * @return the maximum number of life points of the life form
   */
  public int getMaxLifePoints() {
    return maxLifePoints;
  }

  /**
   * @return the attack strength possessed by this life form
   */
  public int getAttackStrength() {
    return attackStrength;
  }

  /**
   * The Life form "drops" the weapon by setting it to null and the method returns
   * the weapon the life form was holding
   * 
   * @return dropped_weapon the weapon the life form was holding
   */
  public Weapon dropWeapon() {
    Weapon droppedWeapon = weapon;
    weapon = null;
    return droppedWeapon;
  }

  /**
   * Checks to see if the life form has a weapon equipped
   * 
   * @return a boolean of true or false
   */
  public boolean hasWeapon() {
    if (currentLifePoints <= 0 || weapon == null) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * If the life form doesn't have a weapon, it will take possession of a new one
   * 
   * @param w the weapon that the life form will "pick up"
   * @return a boolean based on if the life form took the new weapon or not
   */
  public boolean pickUpWeapon(Weapon w) {
    if (weapon == null && currentLifePoints > 0) {
      weapon = w;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Sets the current location of the life form in the environment
   * 
   * @param r the row the life form resides in
   * @param c the column the life form resides in
   */
  public void setLocation(int r, int c) {
    if (r >= -1 && c >= -1) {
      row = r;
      col = c;
    }
  }

  /**
   * @return the row the life form resides in
   */
  public int getRow() {
    return row;
  }

  /**
   * @return the column the life form resides in
   */
  public int getCol() {
    return col;
  }

  /**
   * This will turn the lifeForm the desired direction.
   * 
   * @param direction
   */
  public void turn(String direction) {
    if (direction.equals("North")) {
      currentDirection = "North";
    }

    if (direction.equals("South")) {
      currentDirection = "South";
    }

    if (direction.equals("East")) {
      currentDirection = "East";
    }

    if (direction.equals("West")) {
      currentDirection = "West";
    }
  }

  /**
   * @return the current weapon held by the LifeForm if applicable, otherwise null
   */
  public Weapon getWeapon() {
    if (hasWeapon()) {
      return weapon;
    } else {
      return null;
    }
  }

  /**
   * @return the current direction the lifeform is facing
   */
  public String getDirection() {
    return currentDirection;
  }

  /**
   * "Revives" the Life Form by restoring all of its life points
   */
  public void revive() {
    this.currentLifePoints = maxLifePoints;
  }

  /**
   * Reloads the life form's weapon if the life form has one
   */
  public void reload() {
    if (this.hasWeapon()) {
      (this.weapon).reload();
    }
  }
}