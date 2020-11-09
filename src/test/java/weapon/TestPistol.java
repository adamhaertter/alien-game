package weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exceptions.WeaponException;

public class TestPistol {

  /**
   * Creates a Pistol and tests its firing ranges at multiple distances, including
   * those that work and those outside of its defined range. Checks the border
   * conditions of the gun as well.
   * 
   * @throws WeaponException
   */
  @Test
  public void testFire() throws WeaponException {
    Pistol pistol = new Pistol();
    pistol.reload();
    int damage = pistol.fire(pistol.getMaxRange() - 1);
    int tooFar = pistol.fire(pistol.getMaxRange() + 1);

    // Does it do proper damage?
    assertNotEquals(damage, 0);
    // Can it fire outside of range?
    assertEquals(tooFar, 0);
    for (int i = 0; i < pistol.getMaxAmmo() - 1; i++) {
      pistol.fire(5);
    }
    // Can it fire with no ammo?
    assertEquals(0, pistol.fire(5));
    // Can it fire a negative range?
    try {
      pistol.fire(-1);
    } catch (WeaponException we) {
      assertTrue(true);
    }
  }

}
