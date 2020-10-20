package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exceptions.EnvironmentException;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.Pistol;
import weapon.Weapon;

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
    LifeForm lf = new MockLifeForm("Adam", 20);
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
    LifeForm lf = new MockLifeForm("Emily", 18);
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
    LifeForm lf = new MockLifeForm("Andrew", 14);
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
    LifeForm lf = new MockLifeForm("Eric", 14);
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
    LifeForm lf = new MockLifeForm("Craig", 48);
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
    LifeForm lf = new MockLifeForm("Sheryl", 48);
    boolean success = environment.addLifeForm(lf, 2, 10);
    assertFalse(success);
  }

  /**
   * Creates a Singleton instance of Environment and checks that every cell is
   * still initialized to null values by default.
   */
  @Test
  public void testInitializeAsSingleton() {
    Environment e = Environment.getEnvironment(5, 5);
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        assertNull(e.getLifeForm(i, j));
        assertNull(e.getWeapons(i, j)[0]);
        assertNull(e.getWeapons(i, j)[1]);
      }
    }
  }

  /**
   * Creates an Environment and attempts to add Weapons within the first cell.
   * Checks to see that the weapons are able to be added properly.
   */
  @Test
  public void testAddWeapon() {
    Environment e = new Environment(5, 5);
    Weapon[] weapons = e.getWeapons(0, 0);
    assertNull(weapons[0]);
    assertNull(weapons[1]);
    Weapon w = new Pistol();
    e.addWeapon(w, 0, 0);
    weapons = e.getWeapons(0, 0);
    assertEquals(weapons[0], w);
    assertNull(weapons[1]);
  }

  /**
   * Creates an Environment and immediately adds a Weapon to the first cell.
   * Attempts to remove that weapon and ensures that the weapon is properly
   * removed.
   */
  @Test
  public void testRemoveWeapon() {
    Environment e = new Environment(5, 5);
    Weapon w = new Pistol();
    e.addWeapon(w, 0, 0);
    Weapon[] weapons = e.getWeapons(0, 0);
    assertEquals(weapons[0], w);
    assertNull(weapons[1]);
    e.removeWeapon(w, 0, 0);
    weapons = e.getWeapons(0, 0);
    assertNull(weapons[0]);
    assertNull(weapons[1]);
  }

  /**
   * Creates an Environment and adds two LifeForms on the same row. Checks that
   * the distance between the two LifeForms is calculated correctly.
   * 
   * @throws EnvironmentException
   */
  @Test
  public void testRowDistance() throws EnvironmentException {
    Environment e = new Environment(6, 7);
    LifeForm one = new MockLifeForm("Adam", 10, 0);
    LifeForm two = new MockLifeForm("Scott", 10, 0);
    assertTrue(e.addLifeForm(one, 0, 0));
    assertTrue(e.addLifeForm(two, 0, 5));
    assertEquals(e.getDistance(one, two), 5.0, 0.05);
  }

  /**
   * Creates an Environment and adds two LifeForms on the same column. Checks that
   * the distance between the two LifeForms is calculated correctly.
   * 
   * @throws EnvironmentException
   */
  @Test
  public void testColDistance() throws EnvironmentException {
    Environment e = new Environment(6, 7);
    LifeForm one = new MockLifeForm("Scott", 10, 0);
    LifeForm two = new MockLifeForm("Brennan", 10, 0);
    assertTrue(e.addLifeForm(one, 0, 0));
    assertTrue(e.addLifeForm(two, 5, 0));
    assertEquals(e.getDistance(one, two), 5.0, 0.05);
  }

  /**
   * Creates an Environment and adds two LifeForms of separate rows and columns.
   * Checks that the distance between the two LifeForms is calculated correctly.
   * 
   * @throws EnvironmentException
   */
  @Test
  public void testComplexDistance() throws EnvironmentException {
    Environment e = new Environment(6, 7);
    LifeForm one = new MockLifeForm("Scott", 10, 0);
    LifeForm two = new MockLifeForm("Brennan", 10, 0);
    assertTrue(e.addLifeForm(one, 0, 0));
    assertTrue(e.addLifeForm(two, 3, 4));
    assertEquals(e.getDistance(one, two), 5.0, 0.05);
  }
}
