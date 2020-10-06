package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.WeaponException;

public class TestPlasmaCannon {

  /**
   * @TODO
   * @throws WeaponException
   */
  @Test
  public void testFire() throws WeaponException {
    PlasmaCannon pc = new PlasmaCannon();
    pc.reload();
    int damage = pc.fire(pc.getMaxRange() - 1);
    int tooFar = pc.fire(pc.getMaxRange() + 1);

    // Does it do proper damage?
    assertNotEquals(damage, 0);
    // Can it fire outside of range?
    assertEquals(tooFar, 0);
    for (int i = 0; i < pc.getMaxAmmo() - 1; i++) {
      pc.fire(5);
    }
    // Can it fire with no ammo?
    assertEquals(0, pc.fire(5));
    // Can it fire a negative range?
    try {
      pc.fire(-1);
    } catch (WeaponException we) {
      assertTrue(true);
    }
  }
}
