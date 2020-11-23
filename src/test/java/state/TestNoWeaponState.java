package state;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.MockWeapon;
import weapon.Weapon;

public class TestNoWeaponState {
  @Test
  public void testWeaponInCell() {
    Environment env = new Environment(5, 5);
    LifeForm lf = new MockLifeForm("Terry", 20);
    AIContext state = new AIContext(lf, env);
    AIContext nowep = new AIContext(lf, env);
    Weapon weapon = new MockWeapon();

    env.addWeapon(weapon, 3, 3);
    env.addLifeForm(lf, 3, 3);
    env.removeWeapon(weapon, 3, 3);
    assertEquals(state.getCurrentState(), nowep.getNoWeaponState());
  }

  @Test
  public void testNoWeaponInCell() {
    Environment env = new Environment(5, 5);
    LifeForm lf = new MockLifeForm("Terry", 20);
    AIContext state = new AIContext(lf, env);
    AIContext nowep = new AIContext(lf, env);

    env.addLifeForm(lf, 3, 3);
    assertEquals(state.getCurrentState(), nowep.getNoWeaponState());
  }
  
  @Test
  public void testIfDead() {
    LifeForm lf = new MockLifeForm("Terry", 20);
    Environment env = new Environment(5, 5);
    AIContext context = new AIContext(lf, env);
    Weapon weapon = new MockWeapon();
    
    lf.takeHit(20);
    assertEquals(context.getCurrentState(), context.getDeadState());
    lf.pickUpWeapon(weapon);
    assertEquals(context.getCurrentState(), context.getNoWeaponState());
  }
}