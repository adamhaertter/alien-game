package environment;

import static org.junit.Assert.*;

import exceptions.WeaponException;
import lifeform.Human;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import org.junit.Test;
import weapon.*;

import lifeform.LifeForm;
import lifeform.MockLifeForm;

/**
 * @author Brennan Mulligan - modified by Scott Bucher
 *
 */
public class TestCommand {
  Environment env = Environment.getEnvironment(10, 10);

  /**
   * Test the move command
   */
  @Test
  public void testMoveCommand() {
    env.clearBoard();
    LifeForm life = new MockLifeForm("Joe", 10, 2);
    env.addLifeForm(life, 4, 5);
    CommandInvoker invoker = new CommandInvoker();
    invoker.executeCommand(new MoveCommand(), life, env);
    assertEquals(life.getRow(), 3);
  }

  /**
   * Tests the reload Command
   */
  @Test
  public void testReloadCommand() throws WeaponException {
    env.clearBoard();
    LifeForm life = new MockLifeForm("Joe", 10, 2);
    life.weapon = new Pistol();
    life.weapon.fire(10);
    CommandInvoker invoker = new CommandInvoker();
    invoker.executeCommand(new ReloadCommand(), life, env);
    assertEquals(life.weapon.getMaxAmmo(), life.weapon.getCurrentAmmo());
  }

  /**
   * Test turning north
   */
  @Test
  public void testTurnNorthCommand() {
    env.clearBoard();
    LifeForm life = new MockLifeForm("Joe", 10, 2);
    CommandInvoker invoker = new CommandInvoker();
    invoker.executeCommand(new TurnSouthCommand(), life, env);
    invoker.executeCommand(new TurnNorthCommand(), life, env);
    assertEquals(life.getDirection(), "North");
  }

  /**
   * Test turning south
   */
  @Test
  public void testTurnSouthCommand() {
    env.clearBoard();
    LifeForm life = new MockLifeForm("Joe", 10, 2);
    CommandInvoker invoker = new CommandInvoker();
    invoker.executeCommand(new TurnSouthCommand(), life, env);
    assertEquals(life.getDirection(), "South");
  }

  /**
   * Test turning west
   */
  @Test
  public void testTurnWestCommand() {
    env.clearBoard();
    LifeForm life = new MockLifeForm("Joe", 10, 2);
    CommandInvoker invoker = new CommandInvoker();
    invoker.executeCommand(new TurnWestCommand(), life, env);
    assertEquals(life.getDirection(), "West");
  }

  /**
   * Test turning east
   */
  @Test
  public void testTurnEastCommand() {
    env.clearBoard();
    LifeForm life = new MockLifeForm("Joe", 10, 2);
    CommandInvoker invoker = new CommandInvoker();
    invoker.executeCommand(new TurnEastCommand(), life, env);
    assertEquals(life.getDirection(), "East");
  }

  /**
   * Test dropping gun when there is a free space
   */
  @Test
  public void testDropFree() {
    env.clearBoard();
    LifeForm lf = new MockLifeForm("Joe", 10, 2);
    CommandInvoker invoker = new CommandInvoker();
    Weapon weapon = new Pistol();

    lf.pickUpWeapon(weapon);
    env.addLifeForm(lf, 3, 3);
    invoker.executeCommand(new DropCommand(), lf, env);
    assertNotNull(env.getCell(3, 3).getWeapon1());
  }

  /**
   * Test dropping weapon when it is already full
   */
  @Test
  public void testDropFull() {
    env.clearBoard();
    LifeForm lf = new MockLifeForm("Joe", 10, 2);
    CommandInvoker invoker = new CommandInvoker();
    Weapon weapon = new Pistol();
    Weapon weapon2 = new PlasmaCannon();
    Weapon weapon3 = new ChainGun();

    lf.pickUpWeapon(weapon);
    env.addWeapon(weapon2, 3, 3);
    env.addWeapon(weapon3, 3, 3);
    env.addLifeForm(lf, 3, 3);
    invoker.executeCommand(new DropCommand(), lf, env);
    assertEquals(env.getCell(3, 3).getWeapon1(), weapon2);
  }

  /**
   * Tests the ability of a LifeForm to pick up a weapon from the same cell when
   * not holding a weapon
   */
  @Test
  public void testAcquire() {
    env.clearBoard();
    LifeForm lf = new Human("Ted", 10, 10);
    Weapon weapon = new Pistol();

    env.addWeapon(weapon, 3, 3);
    env.addLifeForm(lf, 3, 3);
    assertFalse(lf.hasWeapon());
    lf.pickUpWeapon(weapon);
    assertTrue(lf.hasWeapon());
    assertEquals(lf.getWeapon(), weapon);
  }

  /**
   * Tests the ability of a LifeForm to pick up a weapon from the same cell when
   * holding a gun
   */
  @Test
  public void testAcquireArmed() {
    env.clearBoard();
    CommandInvoker invoker = new CommandInvoker();
    LifeForm lf = new Human("Ted", 10, 10);
    Weapon weapon = new Pistol();
    Weapon weapon2 = new ChainGun();

    env.addWeapon(weapon, 3, 3);
    env.addWeapon(weapon2, 3, 3);
    env.addLifeForm(lf, 3, 3);
    assertFalse(lf.hasWeapon());
    invoker.executeCommand(new AcquireCommand(), env.getLifeForm(3, 3), env);
    invoker.executeCommand(new AcquireCommand(), env.getLifeForm(3, 3), env);
    assertTrue(lf.hasWeapon());
    assertEquals(lf.getWeapon(), weapon2);
  }
}
