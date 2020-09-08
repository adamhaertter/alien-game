package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import lifeform.LifeForm;

/**
 * The test cases for the Environment class
 *
 */
public class TestEnvironment {

  /**
   * Initializes an Environment and checks that nothing exists within the first
   * cell at the time of initialization (0, 0).
   */
  @Test
  public void testInitialization() {
    Environment environment = new Environment(2, 3);
    LifeForm first = environment.getLifeForm(0, 0);
    assertNull(first);
  }

  /**
   * Initializes an Environment and adds a LifeForm to cell (1,2). Tests whether
   * or not the LifeForm is added to the index correctly.
   */
  @Test
  public void testAddLifeForm() {
    Environment environment = new Environment(2, 3);
    LifeForm lf = new LifeForm("Adam", 20);
    environment.addLifeForm(lf, 1, 2);
    assertEquals(lf, environment.getLifeForm(1, 2));
  }

  /**
   * Initializes an Environment and adds a LifeForm to cell (1,2). Attempts to
   * remove the LifeForm from the same cell. Tests whether or not the LifeForm is
   * removed from the index properly.
   */
  @Test
  public void testRemoveLifeForm() {
    Environment environment = new Environment(2, 3);
    LifeForm lf = new LifeForm("Emily", 18);
    environment.addLifeForm(lf, 1, 2);
    environment.removeLifeForm(1, 2);
    assertNull(environment.getLifeForm(1, 2));
    // The next lines will test that attempting to remove a LifeForm that doesn't
    // exist will still work.
    environment.removeLifeForm(1, 2);
    assertNull(environment.getLifeForm(1, 2));
  }

  /**
   * Initializes an Environment and a LifeForm. Adds a LifeForm to a cell below
   * the lower row boundary. Tests to see that the LifeForm is not properly added.
   */
  @Test
  public void testLowerRowBound() {
    Environment environment = new Environment(2, 3);
    LifeForm lf = new LifeForm("Andrew", 14);
    boolean success = environment.addLifeForm(lf, -1, 3);
    assertFalse(success);
  }

  /**
   * Initializes an Environment and a LifeForm. Adds a LifeForm to a cell above
   * the upper row boundary. Tests to see that the LifeForm is not properly added.
   */
  @Test
  public void testUpperRowBound() {
    Environment environment = new Environment(2, 3);
    LifeForm lf = new LifeForm("Eric", 14);
    boolean success = environment.addLifeForm(lf, 10, 3);
    assertFalse(success);
  }

  /**
   * Initializes an Environment and a LifeForm. Adds a LifeForm to a cell below
   * the lower column boundary. Tests to see that the LifeForm is not properly
   * added.
   */
  @Test
  public void testLowerColBound() {
    Environment environment = new Environment(2, 3);
    LifeForm lf = new LifeForm("Craig", 48);
    boolean success = environment.addLifeForm(lf, 2, -1);
    assertFalse(success);
  }

  /**
   * Initializes an Environment and a LifeForm. Adds a LifeForm to a cell above
   * the lower column boundary. Tests to see that the LifeForm is not properly
   * added.
   */
  @Test
  public void testUpperColBound() {
    Environment environment = new Environment(2, 3);
    LifeForm lf = new LifeForm("Sheryl", 48);
    boolean success = environment.addLifeForm(lf, 2, 10);
    assertFalse(success);
  }
}
