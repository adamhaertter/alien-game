package recovery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Adam Haertter
 *
 */
public class TestRecoveryLinear {

  /**
   * If a LifeForm is set to heal by an amount greater than the damage already
   * dealt to it, the health points should cap out at the maximum possible health
   * points and not exceed that value.
   */
  @Test
  public void testDontOverheal() {
    RecoveryLinear rl = new RecoveryLinear(15);
    int maxLifePts = 20;
    int currentLife = maxLifePts - 10;
    int result = rl.calculateRecovery(currentLife, maxLifePts);
    assertEquals(maxLifePts, result);
  }

  /**
   * When a LifeForm has not been harmed yet, healing it should not change its
   * Life Points.
   */
  @Test
  public void testWhenUnharmed() {
    RecoveryLinear rl = new RecoveryLinear(3);
    int maxLifePts = 30;
    int result = rl.calculateRecovery(maxLifePts, maxLifePts);
    assertEquals(maxLifePts, result);
  }

  /**
   * A LifeForm should be able to heal itself by the full specified amount even if
   * that amount does not heal its life points to full.
   */
  @Test
  public void testReallyHurts() {
    RecoveryLinear rl = new RecoveryLinear(5);
    int maxLifePts = 20;
    int currentLife = maxLifePts - 10;
    int result = rl.calculateRecovery(currentLife, maxLifePts);
    assertEquals(15, result);
  }

  /**
   * If a LifeForm has 0 life points, it should not be able to heal itself. This
   * LifeForm is considered to be dead.
   */
  @Test
  public void testDead() {
    RecoveryLinear rl = new RecoveryLinear(5);
    int maxLifePts = 20;
    int currentLife = 0;
    int result = rl.calculateRecovery(currentLife, maxLifePts);
    assertEquals(0, result);
  }

  /**
   * If a LifeForm takes an amount of damage and is set to heal by that specific
   * amount, it should be able to heal to full health with no problem.
   */
  @Test
  public void testPerfectHeal() {
    RecoveryLinear rl = new RecoveryLinear(10);
    int maxLifePts = 20;
    int currentLife = maxLifePts - 10;
    int result = rl.calculateRecovery(currentLife, maxLifePts);
    assertEquals(maxLifePts, result);
  }

}
