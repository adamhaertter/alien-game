package recovery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Adam Haertter
 *
 */
public class TestRecoveryFractional {

  /**
   * When a LifeForm is healed while at full health, its current health points
   * should not surpass its maximum life points.
   */
  @Test
  public void testFullHealth() {
    RecoveryFractional rf = new RecoveryFractional(.5);
    int maxLifePts = 20;
    int currentLife = maxLifePts;
    int result = rf.calculateRecovery(currentLife, maxLifePts);
    assertEquals(maxLifePts, result);
  }

  /**
   * When a LifeForm is healed by an amount greater than the damage dealt to it,
   * it should not overheal itself. The healing should cap at the maximum life
   * point value.
   */
  @Test
  public void testDontOverheal() {
    RecoveryFractional rf = new RecoveryFractional(0.5);
    int maxLifePts = 20;
    int currentLife = maxLifePts - 5;
    int result = rf.calculateRecovery(currentLife, maxLifePts);
    assertEquals(maxLifePts, result);
  }

  /**
   * When a LifeForm takes an amount of damage, it should be able to heal itself
   * by the specified proportion even if it does not fully refill its health.
   */
  @Test
  public void testHealWholeAmount() {
    RecoveryFractional rf = new RecoveryFractional(0.5);
    int maxLifePts = 20;
    int currentLife = maxLifePts - 10;
    int result = rf.calculateRecovery(currentLife, maxLifePts);
    assertEquals(15, result);
  }

  /**
   * If a LifeForm has 0 health points, it should not be able to heal at all. It
   * is already dead.
   */
  @Test
  public void testDead() {
    RecoveryFractional rf = new RecoveryFractional(0.5);
    int maxLifePts = 20;
    int currentLife = 0;
    int result = rf.calculateRecovery(currentLife, maxLifePts);
    assertEquals(0, result);
  }

}
