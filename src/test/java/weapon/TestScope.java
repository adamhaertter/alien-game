package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import lifeform.LifeForm;
import lifeform.MockLifeForm;

/**
 * Tests the mechanisms and effects of the Scope class on weapons
 * 
 * @author Brennan Mulligan
 */
public class TestScope {
  @Test
  public void TestPistolAndScope() throws AttachmentException, WeaponException {
    Weapon pistol = new Pistol();
    assertEquals(pistol.getBaseDamage(), 10);
    assertEquals(pistol.getMaxRange(), 50);

    Scope scope = new Scope(pistol);
    assertEquals(scope.fire(55), (pistol.fire(pistol.getMaxRange()) + 5));
    assertEquals(scope.getMaxRange(), 60);
  }

  @Test
  public void TestPistolTwoScopes() throws AttachmentException, WeaponException {
    Weapon pistol = new Pistol();
    assertEquals(pistol.getBaseDamage(), 10);
    assertEquals(pistol.getMaxRange(), 50);

    Scope scope1 = new Scope(pistol);
    assertEquals(scope1.fire(55), (pistol.fire(pistol.getMaxRange()) + 5));
    assertEquals(scope1.getMaxRange(), 60);

    Scope scope2 = new Scope(scope1);
    assertEquals(scope2.fire(65), (scope1.fire(scope1.getMaxRange()) + 5));
    assertEquals(scope2.getMaxRange(), 70);
  }

  @Test
  public void TestChainPowerScope() throws AttachmentException, WeaponException {
    Weapon chain = new ChainGun();
    assertEquals(chain.getBaseDamage(), 15);
    assertEquals(chain.getMaxRange(), 60);

    PowerBooster boost = new PowerBooster(chain);
    int shot = chain.fire(10);
    chain.reload();
    assertEquals(boost.fire(10), (shot * 2));

    Scope scope = new Scope(boost);
    assertEquals(scope.fire(65), (boost.fire(boost.getMaxRange()) + 5));
    assertEquals(scope.getMaxRange(), 70);
  }

  @Test
  public void TestPlasmaStabilizerScope() throws AttachmentException, WeaponException {
    Weapon plasma = new PlasmaCannon();
    assertEquals(plasma.getBaseDamage(), 50);
    assertEquals(plasma.getCurrentAmmo(), plasma.getMaxAmmo());
    assertEquals(plasma.getMaxRange(), 40);

    Stabilizer stab = new Stabilizer(plasma);
    assertEquals(stab.fire(10), 62);
    for (int i = stab.getMaxAmmo(); i >= 0; i--) {
      stab.fire(10);
    }
    assertEquals(stab.getCurrentAmmo(), 4);

    Scope scope = new Scope(stab);
    assertEquals(scope.fire(45), (stab.fire(stab.getMaxRange()) + 5));
    assertEquals(scope.getMaxRange(), 50);
  }
}
