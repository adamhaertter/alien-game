package lifeform;

import exceptions.WeaponException;

/**
 * @author Adam Haertter - modified by Scott Bucher
 *
 */
public class MockLifeForm extends LifeForm {

  /**
   * Creates an instance of MockLifeForm, an object nearly identical to the
   * abstract class LifeForm. Mainly used for testing purposes
   * 
   * @param name   The name of the MockLifeForm
   * @param points The Health Points of the MockLifeForm
   */
  public MockLifeForm(String name, int points) {
    super(name, points);
    maxSpeed = 1;
  }

  /**
   * Creates an instance of MockLifeForm, an object nearly identical to the
   * abstract class LifeForm. Mainly used for testing purposes
   * 
   * @param name   The name of the MockLifeForm
   * @param points The Health Points of the MockLifeForm
   * @param atk    The Attack Strength of the MockLifeForm
   */
  public MockLifeForm(String name, int points, int atk) {
    super(name, points, atk);
    maxSpeed = 1;
  }

  /**
   * Prints "Mock"
   */
  public String toString() {
    return "Mock";
  }
  
  /**
   * The same method as in LifeForm, but without the alliance check. (For testing purposes)
   */
  @Override
  public void attack(LifeForm opponent, int distance) {
    if (currentLifePoints > 0) {
      if (weapon != null && weapon.getCurrentAmmo() > 0) {
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
}
