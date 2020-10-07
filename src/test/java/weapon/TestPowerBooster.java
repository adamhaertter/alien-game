package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

/**
 * Tests the mechanisms and effects of the Power Booster class on weapons
 * 
 * @author Brennan Mulligan
 */
public class TestPowerBooster {
  @Test
  public void TestChainPower() throws AttachmentException {
    Weapon chain = new ChainGun();
    assertEquals(chain.getBaseDamage(), 15);

    PowerBooster boost = new PowerBooster(chain);
    assertEquals(chain.getBaseDamage(), 30);
  }

  @Test
  public void TestPistolScopePower() throws AttachmentException {
    Weapon pistol = new Pistol();
    assertEquals(pistol.getBaseDamage(), 10);
    assertEquals(pistol.getMaxRange(), 50);

    Scope scope = new Scope(pistol);
    assertEquals(pistol.getBaseDamage(), 15);
    assertEquals(pistol.getMaxRange(), 60);

    PowerBooster boost = new PowerBooster(pistol);
    assertEquals(pistol.getBaseDamage(), 30);
  }

  @Test
  public void TestChainTwoPowerBoosters() throws AttachmentException {
    Weapon chain = new ChainGun();
    assertEquals(chain.getBaseDamage(), 15);

    PowerBooster boost1 = new PowerBooster(chain);
    assertEquals(chain.getBaseDamage(), 30);

    PowerBooster boost2 = new PowerBooster(chain);
    assertEquals(chain.getBaseDamage(), 60);
  }

  @Test
  public void TestPlasmaStabilizerPowerBooster() throws AttachmentException, WeaponException {
    PlasmaCannon plasma = new PlasmaCannon();
    assertEquals(plasma.getBaseDamage(), 50);
    assertEquals(plasma.getCurrentAmmo(), plasma.getMaxAmmo());
    assertEquals(plasma.getMaxRange(), 40);

    Stabilizer stab1 = new Stabilizer(plasma);
    assertEquals(plasma.getBaseDamage(), 62);
    for (int i = 0; i == plasma.getCurrentAmmo(); i++) {
      plasma.fire(10);
    }
    assertEquals(plasma.getCurrentAmmo(), plasma.getMaxAmmo());
  }
}
