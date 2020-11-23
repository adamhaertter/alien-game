package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;

/**
 * The test cases for the Cell class\
 * 
 * @author Adam Haertter - modified by Josh Lewis
 */
public class TestCell {

  /**
   * At initialization, the Cell should be empty and not contain a LifeForm or any
   * Weapons
   */
  @Test
  public void testInitialization() {
    Cell cell = new Cell();
    assertNull(cell.getLifeForm());
    assertNull(cell.getWeapon1());
    assertNull(cell.getWeapon2());
  }

  /**
   * Checks to see that a singular LifeForm can be added to a cell and be detected
   * within said cell properly.
   */
  @Test
  public void testCreateLifeForm() {
    LifeForm bob = new MockLifeForm("Bob", 40);
    Cell cell = new Cell();
    boolean success = cell.addLifeForm(bob);
    assertTrue(success);
    assertEquals(bob, cell.getLifeForm());
  }

  /**
   * Checks to see if we change the LifeForm held by the Cell that getLifeForm
   * properly responds to this change.
   */
  @Test
  public void testAddLifeForm() {
    LifeForm bob = new MockLifeForm("Bob", 40);
    LifeForm fred = new MockLifeForm("Fred", 40);
    Cell cell = new Cell();
    // The cell is empty so this should work.
    boolean success = cell.addLifeForm(bob);
    assertTrue(success);
    assertEquals(bob, cell.getLifeForm());
    // The cell is not empty so this should fail
    success = cell.addLifeForm(fred);
    assertFalse(success);
    assertEquals(bob, cell.getLifeForm());
  }

  /**
   * Checks to see if after adding a LifeForm to a Cell, that the LifeForm can be
   * removed properly.
   */
  @Test
  public void testRemoveLifeForm() {
    LifeForm bob = new MockLifeForm("Bob", 40);
    Cell cell = new Cell();
    // The cell is empty so this should work.
    boolean success = cell.addLifeForm(bob);
    assertTrue(success);
    assertEquals(bob, cell.getLifeForm());
    cell.removeLifeForm();
    assertNull(cell.getLifeForm());
  }

  /**
   * Creates a cell and fills it with a single weapon. Once that weapon is
   * confirmed to be added correctly, the process is repeated for a second weapon.
   */
  @Test
  public void testAddWeapon() {
    Cell cell = new Cell();
    Weapon pistol = new Pistol();
    // Add one weapon:
    assertTrue(cell.addWeapon(pistol));
    assertEquals(pistol, cell.getWeapon1());
    assertNull(cell.getWeapon2());
    // Add second weapon:
    Weapon cg = new ChainGun();
    assertTrue(cell.addWeapon(cg));
    assertEquals(pistol, cell.getWeapon1());
    assertEquals(cg, cell.getWeapon2());
  }

  /**
   * Creates a cell and immediately fills it with two weapons. Removes the first
   * weapon and checks that the cell's weapons are in order. Then, removes the
   * second weapon and ensures that both have been properly removed.
   */
  @Test
  public void testRemoveWeapon() {
    Cell cell = new Cell();
    Weapon w1 = new Pistol();
    Weapon w2 = new ChainGun();
    // Add both weapons
    assertTrue(cell.addWeapon(w1));
    assertTrue(cell.addWeapon(w2));
    // Remove first weapon:
    assertEquals(w1, cell.removeWeapon(w1));
    assertNull(cell.getWeapon1());
    assertEquals(w2, cell.getWeapon2());
    // Remove second weapon:
    assertEquals(w2, cell.removeWeapon(w2));
    assertNull(cell.getWeapon2());
    assertNull(cell.getWeapon1());
  }

  /**
   * Creates a cell and immediately adds two Weapons to it. Then, checks that both
   * weapon slots are filled and tries to add a third weapon. Checks that this
   * fails and that the weapon slots remain unchanged.
   */
  @Test
  public void testFullWeaponCell() {
    Cell cell = new Cell();
    Weapon w1 = new Pistol();
    Weapon w2 = new ChainGun();
    // Add both weapons
    assertTrue(cell.addWeapon(w1));
    assertTrue(cell.addWeapon(w2));
    // Try to add a third weapon
    Weapon w3 = new PlasmaCannon();
    assertEquals(w1, cell.getWeapon1());
    assertEquals(w2, cell.getWeapon2());
    assertFalse(cell.addWeapon(w3));
    assertEquals(w1, cell.getWeapon1());
    assertEquals(w2, cell.getWeapon2());
  }
}
