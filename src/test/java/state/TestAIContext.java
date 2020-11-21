package state;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import environment.Environment;
import lifeform.LifeForm;
import lifeform.MockLifeForm;

public class TestAIContext {
  @Test
  public void testChangeState() {
    LifeForm lf = new MockLifeForm("Terry", 20);
    Environment env = new Environment(5, 5);
    AIContext context = new AIContext(lf, env);
    lf.takeHit(20);
    assertEquals(context.getCurrentState(), context.getDeadState());
  }
  
  @Test
  public void testGetState() {
    LifeForm lf = new MockLifeForm("Terry", 20);
    Environment env = new Environment(5, 5);
    AIContext context = new AIContext(lf, env);
    lf.takeHit(20);
    assertEquals(context.getCurrentState(), context.getDeadState());
  }
}
