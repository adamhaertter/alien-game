package state;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import environment.Environment;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.MockWeapon;
import weapon.Weapon;

public class TestDeadState {
  @Test
  public void testWithWeapon() {
    LifeForm lf = new MockLifeForm("Terry", 20);
    Environment env = new Environment(5, 5);
    AIContext context = new AIContext(lf, env);
    Weapon weapon = new MockWeapon();

    lf.pickUpWeapon(weapon);
    lf.takeHit(20);
    assertEquals(context.getCurrentState(), context.getDeadState());
  }

  @Test
  public void testWithoutWeapon() {
    LifeForm lf = new MockLifeForm("Terry", 20);
    Environment env = new Environment(5, 5);
    AIContext context = new AIContext(lf, env);

    lf.takeHit(20);
    assertEquals(context.getCurrentState(), context.getDeadState());
  }
}
