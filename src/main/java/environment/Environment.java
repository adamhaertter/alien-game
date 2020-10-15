package environment;

import lifeform.LifeForm;

/**
 * The Environment in which Cells are stored in a 2D Array configuration
 *
 */
public class Environment {
  
  private static Environment e;
  Cell[][] cells;

  /**
   * Sets up the environment by creating a 2D array of cells
   * 
   * @param rows the number of rows for the Cell matrix
   * @param cols the number of columns for the Cell matrix
   */
  private Environment(int rows, int cols) {
    cells = new Cell[rows][cols];
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        cells[r][c] = new Cell();
      }
    }
  }
  
  public Environment getEnvironment(int row, int col) {
    if(e == null) {
      e = new Environment(row, col);      
    }

    return e;
  }

  /**
   * Attempts to add a LifeForm to the specified Cell. Will not add a LifeForm if
   * the Cell is already full or if the parameters fall outside the possible
   * values.
   * 
   * @param lf  the lifeform to be aded to the cell
   * @param row the row index where the lifeform should be added
   * @param col the column index where the lifeform should be added
   * @return false if the lifeform is unable to be added, true if it is added
   *         successfully
   */
  public boolean addLifeForm(LifeForm lf, int row, int col) {
    if (row > cells.length || col > cells[0].length || row < 0 || col < 0) {
      return false;
    }
    Cell inst = cells[row][col];
    if (inst.getLifeForm() == null) {
      inst.addLifeForm(lf);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Removes the LifeForm present in the specified cell if one exists. If invalid
   * parameters are given, this method will do nothing
   * 
   * @param row the row index where the lifeform should be removed from
   * @param col the column index where the lifeform should be removed from
   */
  public void removeLifeForm(int row, int col) {
    if (row > cells.length || col > cells[0].length || row < 0 || col < 0) {
      return;
    }
    Cell inst = cells[row][col];
    inst.removeLifeForm();
  }

  /**
   * Retrieves the LifeForm at the given Cell index
   * 
   * @param row the row index where the lifeform should be stored
   * @param col the column index where the lifeform should be stored
   * @return the LifeForm present in the cell if one exists, null otherwise
   */
  public LifeForm getLifeForm(int row, int col) {
    return cells[row][col].getLifeForm();
  }

}
