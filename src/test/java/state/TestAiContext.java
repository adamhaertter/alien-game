package state;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import environment.Environment;
import lifeform.LifeForm;
import lifeform.MockLifeForm;

/**
 * @author Brennan Mulligan
 *
 */
public class TestAiContext {
  /**
   * Tests the ability of an AIContext to change state.
   */
  @Test
  public void testChangeState() {
    LifeForm lf = new MockLifeForm("Terry", 20);
    Environment env = new Environment(5, 5);
    AiContext context = new AiContext(lf, env);
    lf.takeHit(20);
    assertEquals(context.getCurrentState(), context.getDeadState());
  }

  /**
   * Ensures that the state retrieval matches what was set by the AIContext.
   */
  @Test
  public void testGetState() {
    LifeForm lf = new MockLifeForm("Terry", 20);
    Environment env = new Environment(5, 5);
    AiContext context = new AiContext(lf, env);
    lf.takeHit(20);
    assertEquals(context.getCurrentState(), context.getDeadState());
  }
}
