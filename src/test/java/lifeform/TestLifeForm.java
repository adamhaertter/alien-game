package lifeform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exceptions.WeaponException;
import weapon.MockWeapon;
import weapon.Pistol;
import weapon.Weapon;

/**
 * Tests the functionality provided by the LifeForm class
 *
 * @author Adam Haertter - modified by Brennan Mulligan and Scott Bucher
 */
public class TestLifeForm {

  /**
   * Test the default direction of a life form
   */
  @Test
  public void testDefaultDirection() {
    LifeForm lf = new MockLifeForm("Lab 6", 10, 10);
    assertEquals(lf.getDirection(), "North");
  }

  /**
   * Test turning of a life form
   */
  @Test
  public void testChangeDirection() {
    LifeForm lf = new MockLifeForm("Lab 6", 10, 10);
    assertEquals(lf.getDirection(), "North");
    lf.turn("East");
    assertEquals(lf.getDirection(), "East");
  }

  /**
   * When a LifeForm is created, it should know how many life points it has.
   */
  @Test
  public void testInitializationPoints() {
    MockLifeForm entity;
    entity = new MockLifeForm("Bob", 40);
    assertEquals(40, entity.getCurrentLifePoints());
  }

  /**
   * When a LifeForm is created, it should know its own Name.
   */
  @Test
  public void testInitializationName() {
    MockLifeForm entity = new MockLifeForm("Bob", 40);
    assertEquals("Bob", entity.getName());
  }

  /**
   * Tests the ability of a LifeForm to take damage and alter its current life
   * points in response to the damage dealt.
   */
  @Test
  public void testHitDamage() {
    MockLifeForm entity = new MockLifeForm("Bob", 40);
    entity.takeHit(5);
    assertEquals(35, entity.getCurrentLifePoints());
  }

  /**
   * Tests the functionality of the LifeForm taking damage multiple times in a
   * row. The LifeForm should properly register each hit and account for the
   * damage taken by each.
   */
  @Test
  public void testHitTwice() {
    MockLifeForm entity = new MockLifeForm("Bob", 40);
    entity.takeHit(5);
    assertEquals(35, entity.getCurrentLifePoints());
    entity.takeHit(15);
    assertEquals(20, entity.getCurrentLifePoints());
  }

  /**
   * Sets up two LifeForms, both with 50 HP. When one takes damage from the other,
   * this test checks to make sure that the default attack damage of 1 is dealt to
   * the other.
   */
  @Test
  public void testAttack() {
    MockLifeForm one = new MockLifeForm("One", 50);
    MockLifeForm two = new MockLifeForm("Two", 50);

    one.attack(two, 1);
    assertEquals(two.getCurrentLifePoints(), 49);
  }

  /**
   * Sets up two LifeForms, one of which is already dead. This test ensures that
   * the dead LifeForm cannot deal damage to the other LifeForm using the attack()
   * method.
   */
  @Test
  public void testAttackWhenDead() {
    MockLifeForm one = new MockLifeForm("One", 0);
    MockLifeForm two = new MockLifeForm("Two", 50);

    one.attack(two, 1);
    assertEquals(two.getCurrentLifePoints(), 50);
  }

  /**
   * Creates a LifeForm and a Weapon. Checks that the LifeForm has no weapon
   * before it picks up the weapon and that it does have one after it picks up the
   * weapon.
   */
  @Test
  public void testPickupWeapon() {
    LifeForm lf = new MockLifeForm("Test", 10);
    Weapon weapon = new MockWeapon();
    assertFalse(lf.hasWeapon());
    assertTrue(lf.pickUpWeapon(weapon));
    assertTrue(lf.hasWeapon());
  }

  /**
   * Creates a LifeForm and tests to see that it properly picks up a Weapon. Then,
   * makes sure that it cannot pick up a second weapon while it is already
   * carrying one.
   */
  @Test
  public void testCarryingWeapon() {
    LifeForm lf = new MockLifeForm("Test", 10);
    Weapon weapon = new MockWeapon();
    assertFalse(lf.hasWeapon());
    assertTrue(lf.pickUpWeapon(weapon));
    Weapon weaponTwo = new MockWeapon();
    assertTrue(lf.hasWeapon());
    assertFalse(lf.pickUpWeapon(weaponTwo));
  }

  /**
   * Creates a LifeForm and a Weapon and makes sure the LifeForm properly picks up
   * the weapon. Then, drops the weapon. Makes sure this properly removes the
   * weapon from the life form and that the same weapon is dropped as was picked
   * up.
   */
  @Test
  public void testDropWeapon() {
    LifeForm lf = new MockLifeForm("Test", 10);
    Weapon weapon = new MockWeapon();
    assertFalse(lf.hasWeapon());
    assertTrue(lf.pickUpWeapon(weapon));
    assertTrue(lf.hasWeapon());
    assertEquals(lf.dropWeapon(), weapon);
    assertFalse(lf.hasWeapon());
  }

  /**
   * Creates two LifeForms, one of which is given a gun. Checks to see that the
   * weapon-wielding LifeForm uses its weapon to attack the other LifeForm and
   * that the proper amount of damage is done.
   * 
   * @throws WeaponException
   */
  @Test
  public void testFireWeapon() throws WeaponException {
    LifeForm range = new MockLifeForm("Range", 10, 1);
    LifeForm melee = new MockLifeForm("Melee", 10, 1);
    Weapon weapon = new MockWeapon();
    range.pickUpWeapon(weapon);
    int meleeMaxHealth = melee.getCurrentLifePoints();
    range.attack(melee, 1);
    assertEquals(melee.getCurrentLifePoints(), meleeMaxHealth - weapon.fire(1));
    assertNotEquals(melee.getCurrentLifePoints(), meleeMaxHealth - range.getAttackStrength());
  }

  /**
   * Creates two LifeForms and gives one a Weapon. When the gun has exhausted all
   * of its ammo, the weapon-wielding LifeForm will attack the other LifeForm.
   * Checks to make sure that the LifeForm does melee damage despite having an
   * (empty) weapon.
   * 
   * @throws WeaponException
   */
  @Test
  public void testMeleeDamage() throws WeaponException {
    LifeForm range = new MockLifeForm("Range", 10, 1);
    LifeForm melee = new MockLifeForm("Melee", 10, 1);
    Weapon weapon = new MockWeapon();
    range.pickUpWeapon(weapon);
    int meleeMaxHealth = melee.getCurrentLifePoints();
    while (weapon.getCurrentAmmo() > 0) {
      weapon.fire(1);
    }
    range.attack(melee, 1);
    assertNotEquals(melee.getCurrentLifePoints(), meleeMaxHealth - weapon.fire(1));
    assertEquals(melee.getCurrentLifePoints(), meleeMaxHealth - range.getAttackStrength());
  }

  /**
   * Creates two LifeForms, one of which is given a weapon. The LifeForms are set
   * to be over 5 feet apart and the empty-handed LifeForm attacks the other.
   * Checks to see that no damage is dealt by this melee attack.
   */
  @Test
  public void testMeleeBounds() {
    LifeForm range = new MockLifeForm("Range", 10, 1);
    LifeForm melee = new MockLifeForm("Melee", 10, 1);

    int rangeMaxHealth = range.getCurrentLifePoints();
    melee.attack(range, 10);
    assertEquals(range.getCurrentLifePoints(), rangeMaxHealth);
  }

  /**
   * Creates a LifeForm which is given a Pistol weapon. The LifeForm fires once.
   * Checks to see that there is one less shot. The LifeForm reloads. Checks to
   * see that its at max ammo.
   */
  @Test
  public void testReloadWeapon() {
    LifeForm lf = new MockLifeForm("Test", 10, 1);
    LifeForm opponent = new MockLifeForm("Enemy", 10, 1);
    Pistol pistol = new Pistol();

    lf.pickUpWeapon(pistol);
    assertEquals(pistol.getCurrentAmmo(), pistol.getMaxAmmo());
    lf.attack(opponent, 10);
    assertEquals(pistol.getCurrentAmmo(), (pistol.getMaxAmmo() - 1));
    pistol.reload();
    assertEquals(pistol.getCurrentAmmo(), pistol.getMaxAmmo());
  }

  /**
   * Creates a LifeForm and sets its location to a specific row and column
   * position. Checks that the LifeForm knows where it is located. Then, changes
   * the position and checks to see that the LifeForm tracks this change.
   */
  @Test
  public void testStorePosition() {
    LifeForm lf = new MockLifeForm("Test", 10, 1);
    // First Position
    int r = 10;
    int c = 5;
    lf.setLocation(r, c);
    assertEquals(r, lf.getRow());
    assertEquals(c, lf.getCol());
    // Position Change
    r = 2;
    c = 3;
    lf.setLocation(r, c);
    assertEquals(r, lf.getRow());
    assertEquals(c, lf.getCol());
  }

  /**
   * Creates a LifeForm and checks that its initial position is (-1, -1).
   */
  @Test
  public void testInitialPosition() {
    LifeForm lf = new MockLifeForm("Test", 10, 1);
    assertEquals(lf.getCol(), -1);
    assertEquals(lf.getRow(), -1);
  }

  /**
   * Creates a LifeForm and sets its position to two negative integers. Checks
   * that its true position is (-1, -1). Then, checks the individual effects of
   * negative positions on the row and column values. If either row or column is
   * negative, the LifeForm should be set to position (-1, -1)
   */
  @Test
  public void testNegativePosition() {
    LifeForm lf = new MockLifeForm("Test", 10, 1);
    // Double Negative
    int r = -20;
    int c = -5;
    lf.setLocation(r, c);
    assertEquals(lf.getCol(), -1);
    assertEquals(lf.getRow(), -1);
    // Row Positive
    r = 20;
    c = -10;
    lf.setLocation(r, c);
    assertEquals(lf.getCol(), -1);
    assertEquals(lf.getRow(), -1);
    // Col Positive
    r = -5;
    c = 15;
    lf.setLocation(r, c);
    assertEquals(lf.getCol(), -1);
    assertEquals(lf.getRow(), -1);
  }
}
