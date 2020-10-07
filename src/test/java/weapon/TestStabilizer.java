package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import gameplay.SimpleTimer;

/**
 * Tests the mechanisms and effects of the Stabilizer class on weapons
 * 
 * @author Brennan Mulligan
 */
public class TestStabilizer {
  /**
   * Creates a PlasmaCannon and attaches a Stabilizer to it. Checks that the
   * calculations are done properly at each step.
   * 
   * @throws AttachmentException
   * @throws WeaponException
   */
  @Test
  public void TestPlasmaStabilizer() throws AttachmentException, WeaponException {
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
  }

  /**
   * Creates a PlasmaCannon and attaches 2 Stabilizers to it. Checks that the
   * calculations are done properly at each step.
   * 
   * @throws AttachmentException
   * @throws WeaponException
   */
  @Test
  public void TestPlasmaTwoStabilizers() throws AttachmentException, WeaponException {
    PlasmaCannon plasma = new PlasmaCannon();
    assertEquals(plasma.getBaseDamage(), 50);
    assertEquals(plasma.getCurrentAmmo(), plasma.getMaxAmmo());
    assertEquals(plasma.getMaxRange(), 40);

    Stabilizer stab1 = new Stabilizer(plasma);
    SimpleTimer timer = new SimpleTimer();
    timer.addTimeObserver(stab1);
    assertEquals(stab1.fire(10), 62);
    for (int i = 0; i < stab1.getMaxAmmo(); i++) {
      stab1.fire(10);
      timer.timeChanged();
    }
    assertEquals(stab1.getCurrentAmmo(), stab1.getMaxAmmo());

    Stabilizer stab2 = new Stabilizer(stab1);
    assertEquals(stab2.fire(10), 77);
    timer.addTimeObserver(stab2);
    stab2.reload();
    for (int i = 0; i < stab2.getMaxAmmo(); i++) {
      stab2.fire(10);
      timer.timeChanged();
    }
    assertEquals(stab2.getCurrentAmmo(), stab2.getMaxAmmo());
  }

  /**
   * Creates a Pistol and attaches a Scope and a Stabilizer to it. Checks that the
   * calculations are done properly at each step.
   * 
   * @throws AttachmentException
   * @throws WeaponException
   */
  @Test
  public void TestPistolScopeStabilizer() throws AttachmentException, WeaponException {
    Weapon pistol = new Pistol();
    assertEquals(pistol.getBaseDamage(), 10);
    assertEquals(pistol.getMaxRange(), 50);

    Scope scope = new Scope(pistol);
    assertEquals(scope.fire(55), (pistol.fire(pistol.getMaxRange()) + 5));
    assertEquals(scope.getMaxRange(), 60);

    Stabilizer stab = new Stabilizer(scope);
    SimpleTimer timer = new SimpleTimer();
    timer.addTimeObserver(stab);
    assertEquals(stab.fire(55), (int) Math.floor(scope.fire(55) * 1.25));
    for (int i = 0; i < stab.getMaxAmmo() - 1; i++) {
      stab.fire(10);
      timer.timeChanged();
    }
    assertEquals(stab.getCurrentAmmo(), stab.getMaxAmmo());
  }

  /**
   * Creates a ChainGun and attaches a Booster and a Stabilizer to it. Checks that
   * the calculations are done properly at each step.
   * 
   * @throws AttachmentException
   * @throws WeaponException
   */
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
    SimpleTimer timer = new SimpleTimer();
    timer.addTimeObserver(stab);
    assertEquals(stab.fire(10), (int) (boost.fire(10) * 1.25));
    stab.reload();
    for (int i = 0; i < stab.getMaxAmmo(); i++) {
      stab.fire(10);
      timer.timeChanged();
    }
    assertEquals(stab.getCurrentAmmo(), stab.getMaxAmmo());
  }
}
