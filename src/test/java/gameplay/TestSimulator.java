package gameplay;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import gui.GameGui;
import lifeform.LifeForm;
import state.AIContext;

/**
 * @author Adam Haertter
 *
 */
public class TestSimulator {

  GameGui gui = new GameGui();
  Environment env = gui.getEnvironment();

  /**
   * Tests that the Environment is populated properly. The number of humans and
   * aliens must be equal to the number of lifeforms and weapons, individually.
   */
  @Test
  public void testPopulate() {
    SimpleTimer timer = new SimpleTimer(1);

    env.clearBoard();
    
    int numHumans = 10;
    int numAliens = 15;

    Simulator sim = new Simulator(env, timer, numHumans, numAliens);

    int lifeFormCount = 0;
    int weaponCount = 0;
    for (int r = 0; r < env.getNumRows(); r++) {
      for (int c = 0; c < env.getNumCols(); c++) {
        if (env.getCell(r, c).getLifeForm() != null) {
          // Count the LifeForms in the environment, proof of initialization
          lifeFormCount++;
        }
        if (env.getCell(r, c).getWeapon1() != null) {
          weaponCount++;
        }
        if (env.getCell(r, c).getWeapon2() != null) {
          weaponCount++;
        }
      }
    }

    // Check that it adds the proper number of lifeforms
    assertEquals(lifeFormCount, numHumans + numAliens);
    assertEquals(lifeFormCount, weaponCount);
    assertEquals(weaponCount, numHumans + numAliens);
  }

  /**
   * Tests that when the round passes, all AIContexts are updated by the
   * environment.
   */
  @Test
  public void testUpdateTriggers() {
    SimpleTimer timer = new SimpleTimer(1000);

    env.clearBoard();
    
    Simulator sim = new Simulator(env, timer, 1, 1);
    sim.gui = gui;
    timer.addTimeObserver(sim);

    LifeForm lf;
    
    for (int r = 0; r < env.getNumRows(); r++) {
      for (int c = 0; c < env.getNumCols(); c++) {
        if (env.getCell(r, c).getLifeForm() != null) {
          // Count the LifeForms in the environment, proof of initialization
          lf = env.getCell(r, c).getLifeForm();
          AIContext ai = new AIContext(lf, env);
          // Round 1
          timer.timeChanged();
          assertEquals(ai.myTime, timer.getRound());
          return;
        }
      }
    }
  }

}
