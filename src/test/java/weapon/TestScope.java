package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import gameplay.SimpleTimer;
import lifeform.LifeForm;
import lifeform.MockLifeForm;

/**
 * Tests the mechanisms and effects of the Scope class on weapons
 * 
 * @author Brennan Mulligan
 */
public class TestScope {

  /**
   * Creates a Pistol and attaches a Scope to it. Checks that the calculations are
   * done properly at each step.
   * 
   * @throws AttachmentException
   * @throws WeaponException
   */
  @Test
  public void TestPistolAndScope() throws AttachmentException, WeaponException {
    Weapon pistol = new Pistol();
    assertEquals(pistol.getBaseDamage(), 10);
    assertEquals(pistol.getMaxRange(), 50);

    Scope scope = new Scope(pistol);
    assertEquals(scope.fire(55), (pistol.fire(pistol.getMaxRange()) + 5));
    assertEquals(scope.getMaxRange(), 60);
  }

  /**
   * Creates a Pistol and attaches 2 Scopes to it. Checks that the calculations
   * are done properly at each step.
   * 
   * @throws AttachmentException
   * @throws WeaponException
   */
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

  /**
   * Creates a ChainGun and attaches a Scope and a Booster to it. Checks that the
   * calculations are done properly at each step.
   * 
   * @throws AttachmentException
   * @throws WeaponException
   */
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

  /**
   * Creates a PlasmaCannon and attaches a Stabilizer and a Scope to it. Checks
   * that the calculations are done properly at each step.
   * 
   * @throws AttachmentException
   * @throws WeaponException
   */
  @Test
  public void TestPlasmaStabilizerScope() throws AttachmentException, WeaponException {
    Weapon plasma = new PlasmaCannon();
    assertEquals(plasma.getBaseDamage(), 50);
    assertEquals(plasma.getCurrentAmmo(), plasma.getMaxAmmo());
    assertEquals(plasma.getMaxRange(), 40);

    Stabilizer stab = new Stabilizer(plasma);
    SimpleTimer timer = new SimpleTimer();
    timer.addTimeObserver(stab);
    assertEquals(stab.fire(10), 62);
    System.out.println(stab.getCurrentAmmo());
    for (int i = 0; i <= 3; i++) {
      stab.fire(10);
      timer.timeChanged();
    }
    assertEquals(stab.getCurrentAmmo(), 4);

    Scope scope = new Scope(stab);
    assertEquals(scope.fire(45), 67);
    assertEquals(scope.getMaxRange(), 50);
  }

}
