package state;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import environment.Environment;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.MockWeapon;
import weapon.Weapon;

/**
 * @author Brennan Mulligan
 *
 */
public class TestNoWeaponState {
  /**
   * Tests the behavior of a LifeForm dropping its Weapon in a Cell with a
   * previous weapon.
   */
  @Test
  public void testWeaponInCell() {
    Environment env = new Environment(5, 5);
    LifeForm lf = new MockLifeForm("Terry", 20);
    Weapon weapon = new MockWeapon();    
    env.addWeapon(weapon, 3, 3);

    AiContext state = new AiContext(lf, env);
    AiContext nowep = new AiContext(lf, env);
    env.addLifeForm(lf, 3, 3);
    env.removeWeapon(weapon, 3, 3);
    assertEquals(state.getCurrentState(), nowep.getNoWeaponState());
  }

  /**
   * Tests the behavior of a LifeForm dropping its Weapon in a cell without a
   * previous Weapon.
   */
  @Test
  public void testNoWeaponInCell() {
    Environment env = new Environment(5, 5);
    LifeForm lf = new MockLifeForm("Terry", 20);
    AiContext state = new AiContext(lf, env);
    AiContext nowep = new AiContext(lf, env);

    env.addLifeForm(lf, 3, 3);
    assertEquals(state.getCurrentState(), nowep.getNoWeaponState());
  }

  /**
   * Tests compatibility between NoWeaponState and DeadState.
   */
  @Test
  public void testIfDead() {
    LifeForm lf = new MockLifeForm("Terry", 20);
    Environment env = new Environment(5, 5);
    AiContext context = new AiContext(lf, env);
    Weapon weapon = new MockWeapon();

    lf.takeHit(20);
    assertEquals(context.getCurrentState(), context.getDeadState());
    lf.pickUpWeapon(weapon);
    assertEquals(context.getCurrentState(), context.getNoWeaponState());
  }
}