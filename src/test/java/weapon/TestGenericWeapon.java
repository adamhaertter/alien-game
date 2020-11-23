package weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import exceptions.WeaponException;
import gameplay.SimpleTimer;

/**
 * @author Adam Haertter
 *
 */
public class TestGenericWeapon {

  /**
   * Tests to see if the weapon uses 1 ammo after being fired.
   * 
   * @throws WeaponException
   */
  @Test
  public void testUseAmmo() throws WeaponException {
    MockWeapon weapon = new MockWeapon();
    assertEquals(weapon.getCurrentAmmo(), weapon.getMaxAmmo());
    weapon.fire(1);
    assertEquals(weapon.getCurrentAmmo(), weapon.getMaxAmmo() - 1);
  }

  /**
   * Tests the fire rate of the weapon by making sure the shotsLeft field
   * decreases every time the weapon is fired. Then tests that the weapon cannot
   * be fired when it is out of shotsLeft. Checks that updating the round count
   * resets the shotsLeft field.
   * 
   * @throws WeaponException
   */
  @Test
  public void testFireRate() throws WeaponException {
    MockWeapon weapon = new MockWeapon();
    SimpleTimer timer = new SimpleTimer();
    timer.addTimeObserver(weapon);
    assertEquals(weapon.getShotsLeft(), weapon.getRateOfFire());
    while (weapon.getShotsLeft() > 0) {
      weapon.fire(1);
    }
    assertEquals(weapon.fire(weapon.getMaxRange() - 1), 0);
    assertEquals(weapon.getShotsLeft(), 0);
    timer.timeChanged();
    assertEquals(weapon.getShotsLeft(), weapon.getRateOfFire());
  }

  /**
   * Tests to check that the weapon starts with maximum ammo and that the ammo
   * value decreases after firing. Then, ensures that the weapon can be reloaded
   * properly.
   * 
   * @throws WeaponException
   */
  @Test
  public void testReload() throws WeaponException {
    MockWeapon weapon = new MockWeapon();
    assertEquals(weapon.getCurrentAmmo(), weapon.getMaxAmmo());
    weapon.fire(1);
    assertNotEquals(weapon.getCurrentAmmo(), weapon.getMaxAmmo());
    weapon.reload();
    assertEquals(weapon.getCurrentAmmo(), weapon.getMaxAmmo());
  }

  /**
   * Checks that the weapon starts with the maximum amount of ammo and then
   * empties the clip. Tests that no damage is returned by the fire() method when
   * the clip is empty.
   * 
   * @throws WeaponException
   */
  @Test
  public void testNoAmmo() throws WeaponException {
    MockWeapon weapon = new MockWeapon();
    assertEquals(weapon.getCurrentAmmo(), weapon.getMaxAmmo());
    while (weapon.getCurrentAmmo() > 0) {
      weapon.fire(0);
    }
    assertEquals(weapon.fire(0), 0);
  }

  /**
   * Tests that the weapon starts with the maximum amount of ammo. Fires the
   * weapon outside the maximum range. Checks that the damage dealt by this shot
   * is 0 and that the ammo is decreased from this shot.
   * 
   * @throws WeaponException
   */
  @Test
  public void testTooFar() throws WeaponException {
    MockWeapon weapon = new MockWeapon();
    assertEquals(weapon.getCurrentAmmo(), weapon.getMaxAmmo());
    int tooFar = weapon.fire(weapon.getMaxRange() + 1);
    assertEquals(tooFar, 0);
    assertNotEquals(weapon.getCurrentAmmo(), weapon.getMaxAmmo());
  }

}
