package recovery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lifeform.Alien;

public class TestRecoveryNone {

  /**
   * If a LifeForm has not taken damage, the lack of healing it can do should not
   * alter the life points in any way.
   */
  @Test
  public void testDontOverheal() {
    RecoveryNone rn = new RecoveryNone();
    int maxLifePts = 20;
    int currentLife = maxLifePts;
    assertEquals(maxLifePts, rn.calculateRecovery(currentLife, maxLifePts));
  }

  /**
   * LifeForms with Recovery Behavior "None" are unable to heal themselves. If
   * damage has been taken, they should simply return the value of their current
   * health.
   */
  @Test
  public void testDontRecover() {
    RecoveryNone rn = new RecoveryNone();
    int maxLifePts = 20;
    int currentLife = maxLifePts - 5;
    assertNotEquals(maxLifePts, rn.calculateRecovery(currentLife, maxLifePts));
  }
}
