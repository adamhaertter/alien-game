package lifeform;

import exceptions.RecoveryRateException;
import gameplay.SimpleTimer;
import org.junit.Test;
import recovery.RecoveryLinear;

import static org.junit.Assert.*;

/**
 * @author Adam Haertter - modified by Scott Bucher
 *
 */
public class TestAlien {

  /*
   * Creates an Alien and checks that its max speed is 2
   */
  @Test
  public void testMaxSpeed2() {
    Alien alien = new Alien("Joeb", 20);
    assertEquals(alien.maxSpeed, 2);
  }

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

  /**
   * Tests the default setting of an Alien's attack strength. By default the value
   * should be 10.
   */
  @Test
  public void testDefaultStrength() {
    Alien a = new Alien("Allen", 50);
    assertEquals(a.getAttackStrength(), 10);
  }

  /**
   * Creates an instance of Alien with RecoveryRate 5. Checks that the
   * RecoveryRate is set properly within the constructor.
   *
   * @throws RecoveryRateException
   */
  @Test
  public void testRecoveryRateConstructor() throws RecoveryRateException {
    Alien a = new Alien("Roger", 50, new RecoveryLinear(3), 5);
    assertEquals(a.getRecoveryRate(), 5);
  }

  /**
   * Tests to ensure that an Alien abides by its RecoveryRate patterns on the
   * proper rounds.
   *
   * @throws RecoveryRateException
   */
  @Test
  public void testRecoveryRatePos() throws RecoveryRateException {
    Alien a = new Alien("E.T.", 100, new RecoveryLinear(5), 2);
    SimpleTimer st = new SimpleTimer();
    st.addTimeObserver(a);

    a.takeHit(50);
    st.timeChanged(); // round 1
    assertEquals(a.getCurrentLifePoints(), 50);

    st.timeChanged(); // round 2
    assertNotEquals(a.getCurrentLifePoints(), 50);
    assertEquals(a.getCurrentLifePoints(), 55);
  }

  /**
   * Backup test to ensure that an Alien abides by its RecoveryRate patterns on
   * the proper rounds with different values. Passing this test proves that the
   * values are not hard-coded.
   *
   * @throws RecoveryRateException
   */
  @Test
  public void testRecoveryRatePos2() throws RecoveryRateException {
    Alien a = new Alien("E.T.", 100, new RecoveryLinear(10), 3);
    SimpleTimer st = new SimpleTimer();
    st.addTimeObserver(a);

    a.takeHit(50);
    st.timeChanged(); // round 1
    assertEquals(a.getCurrentLifePoints(), 50);

    st.timeChanged(); // round 2
    assertEquals(a.getCurrentLifePoints(), 50);

    st.timeChanged(); // round 3
    assertNotEquals(a.getCurrentLifePoints(), 50);
    assertEquals(a.getCurrentLifePoints(), 60);
  }

  /**
   * Ensures that if an Alien's RecoveryRate is set to 0, it will not heal.
   *
   * @throws RecoveryRateException
   */
  @Test
  public void testRecoveyRateZero() throws RecoveryRateException {
    Alien a = new Alien("LGM", 50, new RecoveryLinear(5), 0);
    SimpleTimer st = new SimpleTimer();
    st.addTimeObserver(a);

    a.takeHit(25);
    st.timeChanged();
    assertEquals(a.getCurrentLifePoints(), 25);

    st.timeChanged();
    assertEquals(a.getCurrentLifePoints(), 25);
    assertNotEquals(a.getCurrentLifePoints(), 55);
  }

  /**
   * Initializes an Alien with a negative RecoveryRate. This is intended to throw
   * an error. If a RecoveryRateException is caught, the test will pass.
   * Otherwise, the test will fail.
   */
  @Test
  public void testRecoveryRateNeg() {
    Alien a;
    try {
      a = new Alien("Yoda", 50, new RecoveryLinear(5), -4);
      SimpleTimer st = new SimpleTimer();
      st.addTimeObserver(a);
    } catch (RecoveryRateException e) {
      assertTrue(true);
      return;
    }
    fail("RecoveryRateException was never thrown.");
  }

  /**
   * Creates an Alien set to observe a SimpleTimer. The timer speeds through
   * multiple rounds, and then the test checks to ensure that the Alien can keep
   * track of what round it is.
   *
   * @throws RecoveryRateException
   */
  @Test
  public void testTrackTime() throws RecoveryRateException {
    Alien a = new Alien("Time Tracker", 100, new RecoveryLinear(5), 2);
    SimpleTimer st = new SimpleTimer();
    st.addTimeObserver(a);

    for (int r = 0; r < 5; r++) {
      st.timeChanged();
    }

    assertEquals(a.getCurrentRound(), 5);
  }

  /**
   * Creates an Alien a set to observe a SimpleTimer st. The Alien heals once
   * within its recovery period and then is removed from observing the
   * SimpleTimer. When the timer hits the Alien's recovery period again, the
   * Aliens should no longer heal.
   *
   * @throws RecoveryRateException
   */
  @Test
  public void testRemoveObserver() throws RecoveryRateException {
    Alien a = new Alien("Goodbye", 100, new RecoveryLinear(5), 2);
    SimpleTimer st = new SimpleTimer();
    st.addTimeObserver(a);

    a.takeHit(50);
    st.timeChanged();
    assertEquals(a.getCurrentLifePoints(), 50);

    st.timeChanged();
    assertEquals(a.getCurrentLifePoints(), 55);

    st.timeChanged();
    assertEquals(a.getCurrentLifePoints(), 55);

    st.removeTimeObserver(a);
    st.timeChanged();
    assertEquals(a.getCurrentLifePoints(), 55);
  }
}
