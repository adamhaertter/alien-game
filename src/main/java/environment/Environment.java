package environment;

import exceptions.EnvironmentException;
import lifeform.LifeForm;

/**
 * The Environment in which Cells are stored in a 2D Array configuration
 *
 * @author Adam Haertter - modified by Brennan Mulligan with getDistance methods
 */
public class Environment {

  Cell[][] cells;

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
   * successfully
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

    return Math.sqrt(Math.pow(row1 - row2, 2) + Math.pow(col1 - col2, 2));
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
}