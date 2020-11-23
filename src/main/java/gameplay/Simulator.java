package gameplay;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import environment.Cell;
import environment.Environment;
import exceptions.RecoveryRateException;
import gui.ControllerGui;
import gui.GameGui;
import lifeform.Alien;
import lifeform.Human;
import recovery.RecoveryBehavior;
import recovery.RecoveryFractional;
import recovery.RecoveryLinear;
import recovery.RecoveryNone;
import state.AiContext;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;

/**
 * @author Adam Haertter
 *
 */
public class Simulator implements TimerObserver {

  Environment environment;
  public static GameGui gui;
  static SimpleTimer timer;
  int numAliens;
  int numHumans;
  List<Cell> openCells;

  /**
   * Constructs an instance of Simulator that builds the game board with the
   * specified components.
   * 
   * @param e         the global environment
   * @param timer     the timer
   * @param numHumans the number of humans to be made
   * @param numAliens the number of aliens to be made
   */
  public Simulator(Environment e, SimpleTimer timer, int numHumans, int numAliens) {
    this.environment = e;
    this.timer = timer;
    this.numAliens = numAliens;
    this.numHumans = numHumans;

    openCells = new ArrayList<>();
    for (int r = 0; r < environment.getNumRows(); r++) {
      for (int c = 0; c < environment.getNumCols(); c++) {
        openCells.add(environment.getCell(r, c));
      }
    }

    try {
      buildWeapons();
      buildAliens();
      buildHumans();
    } catch (RecoveryRateException e1) {
      e1.printStackTrace();
    }
  }

  /**
   * Main method for running program.
   * @param args
   */
  public static void main(String[] args) {
    gui = new GameGui();
    ControllerGui cgui = new ControllerGui(gui);
    Environment e = gui.getEnvironment();
    // We might want to create a global environment to send out instead here

    // TODO e.addObserver(gui);
    // ^ this method doesn't exist yet?
    SimpleTimer timer = new SimpleTimer(1000);

    Simulator sim = new Simulator(e, timer, 15, 10);

    gui.boardUpdate();

    timer.start();

  }

  /**
   * On a new round, the board and AIContexts are updated.
   */
  public void updateTime(int time) {
    // TODO make the timer work please
    gui.boardUpdate();
    AiContext.myTime = time;
  }

  /**
   * Spawns the proper number of Aliens. Each Alien is assigned a random recovery
   * behavior, random recovery rate, and random health of 20, 25, or 30.
   * 
   * @throws RecoveryRateException
   */
  public void buildAliens() throws RecoveryRateException {
    int row;
    int col;
    RecoveryBehavior behavior;
    int maxHealth;
    int recoveryRate; 
    int control;
    for (int a = 0; a < numAliens; a++) {

      // Set up unique recovery behavior
      control = (int) (Math.random() * 3);
      switch (control) {
        case 1:
          // Recover fractionally between 0% and 50%
          behavior = new RecoveryFractional(Math.random() / 2);
          break;
        case 2:
          // Recover linearly between 1 and 10
          behavior = new RecoveryLinear((int) (Math.random() * 10) + 1);
          break;
        default:
          // No recovery
          behavior = new RecoveryNone();
      }

      // Aliens can have maxHealths of 20, 25, or 30
      control = (int) (Math.random() * 3);
      switch (control) {
        case 1:
          maxHealth = 25;
          break;
        case 2:
          maxHealth = 30;
          break;
        default:
          maxHealth = 20;
      }

      // Recovery Rate can be between 1 and 5 turns
      recoveryRate = (int) (Math.random() * 5 + 1);

      Alien current = new Alien("ALIEN " + a, maxHealth, behavior, recoveryRate);

      // Roll for a cell
      if (openCells.size() > 0) {
        // A cell is open! Add the LifeForm to it.
        int index = (int) (Math.random() * openCells.size());
        openCells.get(index).addLifeForm(current);
        openCells.remove(index);
      } else {
        // Looks like we ran out of open cells. The game won't be any fun like this.
        System.out.println("The Environment is full, try a different number of LifeForms.");
        return;
      }
    }
  }

  /**
   * Spawns the proper number of Humans. Humans are locked to a maxHealth of 25
   * and receive armor from 0 to 9 randomly.
   */
  public void buildHumans() {
    int maxHealth = 25;
    int armorPoints;
    for (int h = 0; h < numHumans; h++) {

      // Humans are locked to one maxHealth, defined above.

      // Armor points can be between 0 and 9, assigned randomly.
      armorPoints = (int) (Math.random() * 10);

      Human current = new Human("HUMAN " + h, maxHealth, armorPoints);

      // Roll for a cell
      if (openCells.size() > 0) {
        // A cell is open! Add the LifeForm to it.
        int index = (int) (Math.random() * openCells.size());
        openCells.get(index).addLifeForm(current);
        openCells.remove(index);
      } else {
        // Looks like we ran out of open cells. The game won't be any fun like this.
        System.out.println("The Environment is full, try a different number of LifeForms.");
        return;
      }
    }
  }

  /**
   * Spawns the proper number of Weapons in the cell. The weapon has an equal
   * chance of being a Pistol, ChainGun, or PlasmaCannon. Weapons can spawn either
   * 1 or 2 to a cell, and spawn with the same number as the number of LifeForms.
   */
  public void buildWeapons() {
    int numWeapons = numAliens + numHumans;
    int control;
    List<Cell> clonedCells = new ArrayList<>();
    for (int w = 0; w < numWeapons; w++) {

      Weapon current;

      // Weapon can spawn as a Pistol, ChainGun, or PlasmaCannon, each with an equal
      // chance to spawn.
      control = (int) (Math.random() * 3);
      switch (control) {
        case 1:
          current = new ChainGun();
          break;
        case 2:
          current = new PlasmaCannon();
          break;
        default:
          current = new Pistol();
      }

      // Roll for a cell
      if (openCells.size() > 0) {
        // A cell is open! Add the LifeForm to it.
        int index = (int) (Math.random() * openCells.size());
        if (openCells.get(index).getWeaponsCount() < 2) {
          openCells.get(index).addWeapon(current);
        }
        if (openCells.get(index).getWeaponsCount() == 2) {
          clonedCells.add(openCells.remove(index));
        }
      } else {
        // Looks like we ran out of open cells. The game won't be any fun like this.
        System.out.println("The Environment is full, try a different number of LifeForms.");
        return;
      }
    }
    // Add the cells with weapons back to the list so we can move on to LifeForms.
    openCells.addAll(clonedCells);
  }
}
