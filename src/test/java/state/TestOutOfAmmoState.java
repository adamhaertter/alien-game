package state;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import environment.Environment;
import exceptions.WeaponException;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.Pistol;

public class TestOutOfAmmoState {
  @Test
  public void testInitialization() throws WeaponException {
    LifeForm lf = new MockLifeForm("Terry", 20);
    Environment env = new Environment(5, 5);
    AIContext context = new AIContext(lf, env);
    Pistol weapon = new Pistol();
    
    lf.pickUpWeapon(weapon);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    assertEquals(context.getCurrentState(), context.getOutOfAmmoState());
  }
  
  @Test
  public void testReload() throws WeaponException {
    LifeForm lf = new MockLifeForm("Terry", 20);
    Environment env = new Environment(5, 5);
    AIContext context = new AIContext(lf, env);
    Pistol weapon = new Pistol();
    
    lf.pickUpWeapon(weapon);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    assertEquals(context.getCurrentState(), context.getOutOfAmmoState());
    lf.reload();
    assertEquals(context.getCurrentState(), context.getHasWeaponState());
  }
  
  @Test
  public void testMoveToCorrectState() throws WeaponException {
    LifeForm lf = new MockLifeForm("Terry", 20);
    Environment env = new Environment(5, 5);
    AIContext context = new AIContext(lf, env);
    Pistol weapon = new Pistol();
    
    lf.pickUpWeapon(weapon);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    assertEquals(context.getCurrentState(), context.getOutOfAmmoState());
  }
  
  @Test
  public void testIfDead() throws WeaponException {
    LifeForm lf = new MockLifeForm("Terry", 20);
    Environment env = new Environment(5, 5);
    AIContext context = new AIContext(lf, env);
    Pistol weapon = new Pistol();
    
    lf.pickUpWeapon(weapon);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.fire(1);
    assertEquals(context.getCurrentState(), context.getOutOfAmmoState());
    lf.takeHit(20);
    lf.reload();
    assertEquals(context.getCurrentState(), context.getOutOfAmmoState());
  }
}