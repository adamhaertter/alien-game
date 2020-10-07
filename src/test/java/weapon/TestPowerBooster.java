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
  public void TestChainPower() throws AttachmentException, WeaponException {
    Weapon chain = new ChainGun();
    int shot = chain.fire(10);
    chain.reload();
    assertEquals(chain.getBaseDamage(), 15);

    PowerBooster boost = new PowerBooster(chain);
    assertEquals(boost.fire(10), (shot * 2));
  }

  @Test
  public void TestPistolScopePower() throws AttachmentException, WeaponException {
    Weapon pistol = new Pistol();
    assertEquals(pistol.getBaseDamage(), 10);
    assertEquals(pistol.getMaxRange(), 50);

    Scope scope = new Scope(pistol);
    assertEquals(scope.fire(55), (pistol.fire(pistol.getMaxRange()) + 5));
    assertEquals(scope.getMaxRange(), 60);

    PowerBooster boost = new PowerBooster(scope);
    int shot = scope.fire(10);
    scope.reload();
    assertEquals(boost.fire(10), (shot * 2));
  }

  @Test
  public void TestChainTwoPowerBoosters() throws AttachmentException, WeaponException {
    Weapon chain = new ChainGun();
    assertEquals(chain.getBaseDamage(), 15);

    PowerBooster boost1 = new PowerBooster(chain);
    int shot = chain.fire(10);
    chain.reload();
    assertEquals(boost1.fire(10), (shot * 2));
    boost1.reload();

    PowerBooster boost2 = new PowerBooster(boost1);
    shot = boost1.fire(10);
    boost1.reload();
    assertEquals(boost2.fire(10), (shot * 2));
  }

  @Test
  public void TestPlasmaStabilizerPowerBooster() throws AttachmentException, WeaponException {
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

    PowerBooster boost = new PowerBooster(stab);
    int shot = boost.fire(10);
    boost.reload();
    assertEquals(boost.fire(10), (shot * 2));
  }
}
