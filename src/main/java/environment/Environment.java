package environment;

import exceptions.EnvironmentException;
import exceptions.WeaponException;
import lifeform.LifeForm;
import weapon.Weapon;

/**
 * The Environment in which Cells are stored in a 2D Array configuration
 *
 * @author Adam Haertter - modified by Brennan Mulligan, Scott Bucher and Josh
 *         Lewis
 */
public class Environment implements Commands {

  Cell[][] cells;
  private static Environment uniqueInstance;

  /**
   * Sets up the environment by creating a 2D array of cells
   *
   * @param rows the number of rows for the Cell matrix
   * @param cols the number of columns for the Cell matrix
   */
  public Environment(int rows, int cols) {
    cells = new Cell[rows][cols];
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        cells[r][c] = new Cell();
      }
    }
  }

  /**
   * Attempts to add a LifeForm to the specified Cell. Will not add a LifeForm if
   * the Cell is already full or if the parameters fall outside the possible
   * values.
   *
   * @param lf  the life form to be added to the cell
   * @param row the row index where the life form should be added
   * @param col the column index where the life form should be added
   * @return false if the life form is unable to be added, true if it is added
   *         successfully
   */
  public boolean addLifeForm(LifeForm lf, int row, int col) {
    if (row > cells.length || col > cells[0].length || row < 0 || col < 0) {
      return false;
    }
    Cell inst = cells[row][col];
    if (inst.getLifeForm() == null) {
      inst.addLifeForm(lf);
      lf.setLocation(row, col);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Removes the LifeForm present in the specified cell if one exists. If invalid
   * parameters are given, this method will do nothing
   *
   * @param row the row index where the life form should be removed from
   * @param col the column index where the life form should be removed from
   */
  public void removeLifeForm(int row, int col) {
    if (row < cells.length || col < cells[0].length || row <= 0 || col <= 0) {
      Cell inst = cells[row][col];
      inst.removeLifeForm();
    }
  }

  /**
   * Retrieves the LifeForm at the given Cell index
   *
   * @param row the row index where the life form should be stored
   * @param col the column index where the life form should be stored
   * @return the LifeForm present in the cell if one exists, null otherwise
   */
  public LifeForm getLifeForm(int row, int col) {
    return cells[row][col].getLifeForm();

  }

  /**
   * @param row1 the row of the first cell
   * @param col1 the column of the first cell
   * @param row2 the row of the second cell
   * @param col2 the column of the second cell
   * @return the distance between the first and second cell
   */
  public double getDistance(int row1, int col1, int row2, int col2) throws EnvironmentException {
    if (row1 >= cells.length || col1 >= cells[0].length || row1 < 0 || col1 < 0) {
      if (row2 >= cells.length || col2 >= cells[0].length || row2 < 0 || col2 < 0) {
        throw new EnvironmentException("One or Both Areas Are Invalid");
      }
    }

    return 5.0 * Math.sqrt(Math.pow(row1 - row2, 2) + Math.pow(col1 - col2, 2));
  }

  /**
   * @param life1 the first life form
   * @param life2 the second life form
   * @return the distance between the first and second life form
   */
  public double getDistance(LifeForm life1, LifeForm life2) throws EnvironmentException {
    int row1 = life1.getRow();
    int col1 = life1.getCol();
    int row2 = life2.getRow();
    int col2 = life2.getCol();
    return getDistance(row1, col1, row2, col2);
  }

  /**
   * Removes all items from the cells to clear the board
   */
  public void clearBoard() {
    for (int r = 0; r < this.cells.length; r++) {
      for (int c = 0; c < this.cells[0].length; c++) {
        cells[r][c] = new Cell();
      }
    }
  }

  /**
   * Add a weapon to this certain cell
   *
   * @param weapon - weapon being added to this cell
   * @param row    - row of the cell
   * @param col    - column of the cell
   * @return true if the weapon was added, false if it failed
   */
  public boolean addWeapon(Weapon weapon, int row, int col) {
    if (row >= cells.length || col >= cells[0].length || row < 0 || col < 0) {
      return false;
    }
    return cells[row][col].addWeapon(weapon);
  }

  /**
   * Removes the specified weapon from the cell
   *
   * @param weapon - weapon to be removed from the cell
   * @param row    - row of the cell
   * @param col    = coulmn of the cell
   * @return - the weapon removed from the cell (null if it wasn't removed)
   */
  public Weapon removeWeapon(Weapon weapon, int row, int col) {
    if (row >= cells.length || col >= cells[0].length || row < 0 || col < 0) {
      return null;
    }
    if (cells[row][col].wepOne == weapon) {
      Weapon removedWeapon = cells[row][col].wepOne;
      cells[row][col].wepOne = null;
      return removedWeapon;
    } else if (cells[row][col].wepTwo == weapon) {
      Weapon removedWeapon = cells[row][col].wepTwo;
      cells[row][col].wepTwo = null;
      return removedWeapon;
    }
    return null;
  }

  /**
   * @param row the row index to pull Weapons from
   * @param col the column index to pull Weapons from
   * @return the array of weapons present in a specified cell
   */
  public Weapon[] getWeapons(int row, int col) {
    return new Weapon[] { cells[row][col].wepOne, cells[row][col].wepTwo };
  }

  /**
   * @param rows the number of rows for the Environment
   * @param cols the number of columns for the Environment
   * @return the Singleton instance of the Environment
   */
  public static Environment getEnvironment(int rows, int cols) {
    if (uniqueInstance == null) {
      uniqueInstance = new Environment(rows, cols);
    }

    return uniqueInstance;
  }

  /**
   * @return the number of rows in the Environment
   */
  public int getNumRows() {
    return cells.length;
  }

  /**
   * @return the number of columns in the Environment.
   */
  public int getNumCols() {
    return cells[0].length;
  }

  @Override
  public void reloadCommand(LifeForm lf) {
    if (lf.hasWeapon()) {
      lf.weapon.reload();
    }
  }

  @Override
  public void turnNorthCommand(LifeForm lf) {
    lf.turn("North");
  }

  @Override
  public void turnSouthCommand(LifeForm lf) {
    lf.turn("South");

  }

  @Override
  public void turnWestCommand(LifeForm lf) {
    lf.turn("West");

  }

  @Override
  public void turnEastCommand(LifeForm lf) {
    lf.turn("East");
  }

  @Override
  public void moveCommand(LifeForm life) {
    if (life.currentDirection.equals("North") && (getNumRows() - life.maxSpeed) > 0) {
      if (cells[life.getRow() - life.maxSpeed][life.getCol()].getLifeForm() == null) {
        removeLifeForm(life.getRow(), life.getCol());
        addLifeForm(life, life.getRow() - life.maxSpeed, life.getCol());
      }
    } else {
      if (cells[0][life.getCol()].getLifeForm() == null) {
        removeLifeForm(0, life.getCol());
        addLifeForm(life, 0, life.getCol());
      }
    }

    if (life.currentDirection.equals("South") && (getNumRows() + life.maxSpeed) < getNumRows()) {
      if (cells[life.getRow() + life.maxSpeed][life.getCol()].getLifeForm() == null) {
        removeLifeForm(life.getRow(), life.getCol());
        addLifeForm(life, life.getRow() + life.maxSpeed, life.getCol());
      }
    } else {
      if (cells[getNumRows() - 1][life.getCol()].getLifeForm() == null) {
        removeLifeForm(getNumRows() - 1, life.getCol());
        addLifeForm(life, getNumRows() - 1, life.getCol());
      }
    }

    if (life.currentDirection.equals("West") && (getNumCols() - life.maxSpeed) > 0) {
      if (cells[life.getRow()][life.getCol() - life.maxSpeed].getLifeForm() == null) {
        removeLifeForm(life.getRow(), life.getCol());
        addLifeForm(life, life.getRow() - life.maxSpeed, life.getCol());
      }
    } else {
      if (cells[life.getRow()][0].getLifeForm() == null) {
        removeLifeForm(life.getRow(), 0);
        addLifeForm(life, life.getRow(), 0);
      }
    }

    if (life.currentDirection.equals("East") && (getNumCols() + life.maxSpeed) > 0) {
      if (cells[life.getRow()][life.getCol() + life.maxSpeed].getLifeForm() == null) {
        removeLifeForm(life.getRow(), life.getCol());
        addLifeForm(life, life.getRow() + life.maxSpeed, life.getCol());
      }
    } else {
      if (cells[life.getRow()][getNumCols() - 1].getLifeForm() == null) {
        removeLifeForm(life.getRow(), getNumCols() - 1);
        addLifeForm(life, life.getRow(), getNumCols() - 1);
      }
    }
  }

  @Override
  public void attackCommand(LifeForm lf) throws EnvironmentException, WeaponException {
    // Find and attack closest target in the line of sight of the LifeForm
    String direction = lf.getDirection();
    // North = decrease col
    // South = increase col
    // West = decrease row
    // East = increase row

    int distance;
    if (direction.equalsIgnoreCase("north") || direction.equalsIgnoreCase("south")) {
      // Dealing with cols
      distance = direction.equalsIgnoreCase("north") ? lf.getCol() - 1 : (cells.length - lf.getCol());
      Cell checkCell = null;
      for (int i = 1; i <= distance; i++) {
        checkCell = direction.equalsIgnoreCase("north") ? cells[lf.getRow()][lf.getCol() + 1] : cells[lf.getRow()][lf.getCol() - 1];
        if(checkCell.getLifeForm() != null) {
          // We found a close enough life form!
          i = distance + 1;
          lf.attack(checkCell.getLifeForm(), ((int) getDistance(lf, checkCell.getLifeForm())));
        }
      }

      // We never found someone to attack but we should still fire.... right?
      if (checkCell == null && lf.hasWeapon()) lf.getWeapon().fire(distance);
    } else {
      // Dealing with rows
      distance = direction.equalsIgnoreCase("west") ? lf.getRow() - 1 : (cells[0].length - lf.getRow());
      Cell checkCell = null;
      for (int i = 1; i <= distance; i++) {
        checkCell = direction.equalsIgnoreCase("west") ? cells[lf.getRow() + 1][lf.getCol()] : cells[lf.getRow() - 1][lf.getCol()];
        if(checkCell.getLifeForm() != null) {
          // We found a close enough life form!
          i = distance + 1;
          lf.attack(checkCell.getLifeForm(), ((int) getDistance(lf, checkCell.getLifeForm())));
        }
      }

      // We never found someone to attack but we should still fire.... right?
      if (checkCell == null && lf.hasWeapon()) lf.getWeapon().fire(distance);
    }
  }

  @Override
  public void dropCommand(LifeForm lf) {
    if (addWeapon(lf.weapon, lf.getRow(), lf.getCol())) {
      lf.dropWeapon();
    }
  }

  @Override
  public void acquireCommand(LifeForm lf) {
    Cell cell = cells[lf.getRow()][lf.getCol()];
    // There are at least one weapon in this cell
    if (cell.wepOne != null || cell.wepTwo != null) {
      // Check if the life form already has a weapon
      if (lf.hasWeapon()) {
        Weapon weapon;
        // Try and get a weapon from the first and then second cell spot
        if (cell.wepOne != null) {
          weapon = cell.getWeapon1();
          cell.wepOne = lf.dropWeapon();
        } else {
          weapon = cell.getWeapon2();
          cell.wepTwo = lf.dropWeapon();
        }
        lf.pickUpWeapon(weapon);
      } else {
        // pickup weapon from either spot 1 or two
        lf.pickUpWeapon(cell.wepOne != null && cell.wepTwo != null ? cell.removeWeapon(cell.wepOne)
            : cell.removeWeapon(cell.wepTwo));
      }
    }
  }
}