package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

/**
 * Tests the mechanisms and effects of the Stabilizer class on weapons
 * 
 * @author Brennan Mulligan
 */
public class TestStabilizer {
  @Test
  public void TestPlasmaStabilizer() throws AttachmentException, WeaponException {
    PlasmaCannon plasma = new PlasmaCannon();
    assertEquals(plasma.getBaseDamage(), 50);
    assertEquals(plasma.getCurrentAmmo(), plasma.getMaxAmmo());
    assertEquals(plasma.getMaxRange(), 40);

    Stabilizer stab = new Stabilizer(plasma);
    assertEquals(stab.fire(10), 62);
    for (int i = stab.getMaxAmmo(); i >= 0; i--) {
      stab.fire(10);
    }
    assertEquals(stab.getCurrentAmmo(), stab.getMaxAmmo());
  }

  @Test
  public void TestPlasmaTwoStabilizers() throws AttachmentException, WeaponException {
    PlasmaCannon plasma = new PlasmaCannon();
    assertEquals(plasma.getBaseDamage(), 50);
    assertEquals(plasma.getCurrentAmmo(), plasma.getMaxAmmo());
    assertEquals(plasma.getMaxRange(), 40);

    Stabilizer stab1 = new Stabilizer(plasma);
    assertEquals(stab1.fire(10), 62);
    for (int i = stab1.getMaxAmmo(); i >= 0; i--) {
      stab1.fire(10);
    }
    assertEquals(stab1.getCurrentAmmo(), stab1.getMaxAmmo());

    Stabilizer stab2 = new Stabilizer(stab1);
    assertEquals(stab2.fire(10), (int) (stab1.fire(10) * 1.25));
    for (int i = stab2.getMaxAmmo(); i >= 0; i--) {
      stab2.fire(10);
    }
    assertEquals(stab2.getCurrentAmmo(), stab2.getMaxAmmo());
  }

  @Test
  public void TestPistolScopeStabilizer() throws AttachmentException, WeaponException {
    Weapon pistol = new Pistol();
    assertEquals(pistol.getBaseDamage(), 10);
    assertEquals(pistol.getMaxRange(), 50);

    Scope scope = new Scope(pistol);
    assertEquals(scope.fire(55), (pistol.fire(pistol.getMaxRange()) + 5));
    assertEquals(scope.getMaxRange(), 60);

    Stabilizer stab = new Stabilizer(scope);
    assertEquals(stab.fire(55), (int) Math.floor(scope.fire(55) * 1.25));
    for (int i = stab.getMaxAmmo(); i >= 0; i--) {
      stab.fire(10);
    }
    assertEquals(stab.getCurrentAmmo(), stab.getMaxAmmo());
  }

  @Test
  public void TestChainPowerStabilizer() throws AttachmentException, WeaponException {
    Weapon chain = new ChainGun();
    assertEquals(chain.getBaseDamage(), 15);
    assertEquals(chain.getCurrentAmmo(), chain.getMaxAmmo());

    PowerBooster boost = new PowerBooster(chain);
    int shot = chain.fire(10);
    chain.reload();
    assertEquals(boost.fire(10), (shot * 2));

    Stabilizer stab = new Stabilizer(boost);
    assertEquals(stab.fire(10), (int) (boost.fire(10) * 1.25));
    for (int i = stab.getMaxAmmo(); i >= 0; i--) {
      stab.fire(10);
    }
    assertEquals(stab.getCurrentAmmo(), stab.getMaxAmmo());
  }
}
