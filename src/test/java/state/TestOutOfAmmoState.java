package state;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import environment.Environment;
import exceptions.WeaponException;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.Pistol;

/**
 * @author Brennan Mulligan
 *
 */
public class TestOutOfAmmoState {

  /**
   * Ensures that OutOfAmmoState works properly on initialization.
   * 
   * @throws WeaponException
   */
  @Test
  public void testInitialization() throws WeaponException {
    LifeForm lf = new MockLifeForm("Terry", 20);
    Environment env = new Environment(5, 5);
    AiContext context = new AiContext(lf, env);
    Pistol weapon = new Pistol();

    lf.pickUpWeapon(weapon);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    assertEquals(context.getCurrentState(), context.getOutOfAmmoState());
  }

  /**
   * Ensures that the Weapon can be reloaded in OutOfAmmoState.
   * 
   * @throws WeaponException
   */
  @Test
  public void testReload() throws WeaponException {
    LifeForm lf = new MockLifeForm("Terry", 20);
    Environment env = new Environment(5, 5);
    AiContext context = new AiContext(lf, env);
    Pistol weapon = new Pistol();

    lf.pickUpWeapon(weapon);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    assertEquals(context.getCurrentState(), context.getOutOfAmmoState());
    lf.reload();
    assertEquals(context.getCurrentState(), context.getHasWeaponState());
  }

  /**
   * Ensures that the OutOfAmmoState transitions to the proper state when
   * necessary.
   * 
   * @throws WeaponException
   */
  @Test
  public void testMoveToCorrectState() throws WeaponException {
    LifeForm lf = new MockLifeForm("Terry", 20);
    Environment env = new Environment(5, 5);
    AiContext context = new AiContext(lf, env);
    Pistol weapon = new Pistol();

    lf.pickUpWeapon(weapon);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    assertEquals(context.getCurrentState(), context.getOutOfAmmoState());
  }

  /**
   * Tests the behavior of an OutOfAmmoState when the LifeForm has died.
   * 
   * @throws WeaponException
   */
  @Test
  public void testIfDead() throws WeaponException {
    LifeForm lf = new MockLifeForm("Terry", 20);
    Environment env = new Environment(5, 5);
    AiContext context = new AiContext(lf, env);
    Pistol weapon = new Pistol();

    lf.pickUpWeapon(weapon);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    assertEquals(context.getCurrentState(), context.getOutOfAmmoState());
    lf.takeHit(20);
    lf.reload();
    assertEquals(context.getCurrentState(), context.getOutOfAmmoState());
  }
}