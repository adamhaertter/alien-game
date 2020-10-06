package lifeform;

import exceptions.WeaponException;
import weapon.Weapon;

/**
 * Keeps track of the information associated with as simple life form. Also
 * provides the functionality related to the life form.
 * 
 * @author Adam Haertter - modified by Brennan Mulligan with Weapon methods
 */
public abstract class LifeForm {

  private String myName;
  protected int currentLifePoints;
  protected int attackStrength;
  protected Weapon weapon;

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
   * @param distance the distance between the two life forms
   */
  public void attack(LifeForm opponent, int distance) {
    if (currentLifePoints > 0) {
      if (weapon != null && weapon.getShotsLeft() != 0) {
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
    Weapon dropped_weapon = weapon;
    weapon = null;
    return dropped_weapon;
  }

  /**
   * Checks to see if the life form has a weapon equipped
   * 
   * @return a boolean of true or false
   */
  public boolean hasWeapon() {
    if (weapon == null) {
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
    if(weapon == null) {
      weapon = w;
      return true;
    } else {
      return false;
    }
  }
}
