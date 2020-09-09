package lifeform;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import recovery.RecoveryLinear;

public class TestAlien {

  /**
   * Tests the Alien's initialization process by checking its default life points,
   * name, and recovery rate.
   */
  @Test
  public void testInitialization() {
    Alien alien = new Alien("Mr. Alien", 20);
    assertEquals(alien.getMaxLifePoints(), 20);
    assertEquals(alien.getName(), "Mr. Alien");
    assertEquals(alien.getRecoveryRate(), 0);
  }

  /**
   * Tests the Alien's ability to heal using a Linear Recovery Behavior. Ensures
   * that damage and healing processes are executed properly.
   */
  @Test
  public void testLinearHeal() {
    Alien alien = new Alien("Linear", 10, new RecoveryLinear(5));
    alien.takeHit(6);
    alien.recover();
    assertEquals(alien.getCurrentLifePoints(), 9);
  }
}
