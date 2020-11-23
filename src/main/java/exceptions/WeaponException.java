package exceptions;

/**
 * @author Brennan Mulligan
 *
 */
public class WeaponException extends Exception {

  /**
   * Initializes a basic Weapon Exception for the program
   * 
   * @param message the message to be pushed upon receiving the error
   */
  public WeaponException(String message) {
    super(message);
  }
}
