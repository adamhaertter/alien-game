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
    assertEquals(plasma.getBaseDamage(), 62);
    for (int i = 0; i == plasma.getCurrentAmmo(); i++) {
      plasma.fire(10);
    }
    assertEquals(plasma.getCurrentAmmo(), plasma.getMaxAmmo());
  }

  @Test
  public void TestPlasmaTwoStabilizers() throws AttachmentException, WeaponException {
    PlasmaCannon plasma = new PlasmaCannon();
    assertEquals(plasma.getBaseDamage(), 50);
    assertEquals(plasma.getCurrentAmmo(), plasma.getMaxAmmo());
    assertEquals(plasma.getMaxRange(), 40);

    Stabilizer stab1 = new Stabilizer(plasma);
    assertEquals(plasma.getBaseDamage(), 62);
    for (int i = 0; i == plasma.getMaxAmmo(); i++) {
      plasma.fire(10);
    }
    assertEquals(plasma.getCurrentAmmo(), plasma.getMaxAmmo());

    Stabilizer stab2 = new Stabilizer(plasma);
    assertEquals(plasma.getBaseDamage(), 77);
    for (int i = 0; i == plasma.getMaxAmmo(); i++) {
      plasma.fire(10);
    }
    assertEquals(plasma.getCurrentAmmo(), plasma.getMaxAmmo());
  }

  @Test
  public void TestPistolScopeStabilizer() throws AttachmentException, WeaponException {
    Weapon pistol = new Pistol();
    assertEquals(pistol.getBaseDamage(), 10);
    assertEquals(pistol.getMaxRange(), 50);
    assertEquals(pistol.getCurrentAmmo(), pistol.getMaxAmmo());

    Scope scope = new Scope(pistol);
    assertEquals(pistol.getBaseDamage(), 15);
    assertEquals(pistol.getMaxRange(), 60);

    Stabilizer stab = new Stabilizer(pistol);
    assertEquals(pistol.getBaseDamage(), 18);
    for (int i = 0; i == pistol.getCurrentAmmo(); i++) {
      pistol.fire(10);
    }
    assertEquals(pistol.getCurrentAmmo(), pistol.getMaxAmmo());
  }

  @Test
  public void TestChainPowerStabilizer() throws AttachmentException, WeaponException {
    Weapon chain = new ChainGun();
    assertEquals(chain.getBaseDamage(), 15);
    assertEquals(chain.getCurrentAmmo(), chain.getMaxAmmo());

    PowerBooster boost = new PowerBooster(chain);
    assertEquals(chain.getBaseDamage(), 30);

    Stabilizer stab = new Stabilizer(chain);
    assertEquals(chain.getBaseDamage(), 37);
    for (int i = 0; i < chain.getCurrentAmmo(); i++) {
      chain.fire(10);
    }
    assertEquals(chain.getCurrentAmmo(), chain.getMaxAmmo());
  }
}
