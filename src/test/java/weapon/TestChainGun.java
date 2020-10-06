package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.WeaponException;

public class TestChainGun {

  /**
   * @TODO
   * @throws WeaponException
   */
  @Test
  public void testFire() throws WeaponException {
    ChainGun cg = new ChainGun();
    cg.reload();
    int damage = cg.fire(cg.getMaxRange() - 1);
    int tooFar = cg.fire(cg.getMaxRange() + 1);

    // Does it do proper damage?
    assertNotEquals(damage, 0);
    // Can it fire outside of range?
    assertEquals(tooFar, 0);
    for (int i = 0; i < cg.getMaxAmmo() - 1; i++) {
      cg.fire(5);
    }
    // Can it fire with no ammo?
    assertEquals(0, cg.fire(5));
    // Can it fire a negative range?
    try {
      cg.fire(-1);
    } catch (WeaponException we) {
      assertTrue(true);
    }
  }
}
