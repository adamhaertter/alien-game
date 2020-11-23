package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import gameplay.SimpleTimer;

/**
 * Tests the mechanisms and effects of the Power Booster class on weapons
 * 
 * @author Brennan Mulligan - modified by Adam Haertter
 */
public class TestPowerBooster {
  /**
   * Creates a ChainGun and attaches a Booster to it. Checks that the calculations
   * are done properly at each step.
   * 
   * @throws AttachmentException
   * @throws WeaponException
   */
  @Test
  public void testChainPower() throws AttachmentException, WeaponException {
    Weapon chain = new ChainGun();
    int shot = chain.fire(10);
    chain.reload();
    assertEquals(chain.getBaseDamage(), 15);

    PowerBooster boost = new PowerBooster(chain);
    assertEquals(boost.fire(10), (shot * 2));
  }

  /**
   * Creates a Pistol and attaches a Scope and a Booster to it. Checks that the
   * calculations are done properly at each step.
   * 
   * @throws AttachmentException
   * @throws WeaponException
   */
  @Test
  public void testPistolScopePower() throws AttachmentException, WeaponException {
    Weapon pistol = new Pistol();
    assertEquals(pistol.getBaseDamage(), 10);
    assertEquals(pistol.getMaxRange(), 50);

    Scope scope = new Scope(pistol);
    assertEquals(scope.fire(55), (pistol.fire(pistol.getMaxRange()) + 5));
    assertEquals(scope.getMaxRange(), 60);

    PowerBooster boost = new PowerBooster(scope);
    scope.reload();
    int shot = scope.fire(10);
    scope.reload();
    assertEquals(boost.fire(10), (shot * 2));
  }

  /**
   * Creates a ChainGun and attaches 2 Boosters to it. Checks that the
   * calculations are done properly at each step.
   * 
   * @throws AttachmentException
   * @throws WeaponException
   */
  @Test
  public void testChainTwoPowerBoosters() throws AttachmentException, WeaponException {
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

  /**
   * Creates a PlasmaCannon and attaches a Stabilizer and a Booster to it. Checks
   * that the calculations are done properly at each step.
   * 
   * @throws AttachmentException
   * @throws WeaponException
   */
  @Test
  public void testPlasmaStabilizerPowerBooster() throws AttachmentException, WeaponException {
    PlasmaCannon plasma = new PlasmaCannon();
    assertEquals(plasma.getBaseDamage(), 50);
    assertEquals(plasma.getCurrentAmmo(), plasma.getMaxAmmo());
    assertEquals(plasma.getMaxRange(), 40);

    Stabilizer stab = new Stabilizer(plasma);
    SimpleTimer timer = new SimpleTimer();
    timer.addTimeObserver(stab);
    assertEquals(stab.fire(10), 62);
    for (int i = 0; i <= 3; i++) {
      stab.fire(10);
      timer.timeChanged();
    }
    assertEquals(stab.getCurrentAmmo(), stab.getMaxAmmo());

    PowerBooster boost = new PowerBooster(stab);
    int shot = stab.fire(10);
    boost.reload();
    assertEquals(boost.fire(10), (shot * 2));
  }
}
