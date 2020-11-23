package exceptions;

/**
 * @author Brennan Mulligan
 */
public class EnvironmentException extends Exception {

  /**
   * Initializes a basic Environment Exception for the program
   * 
   * @param message the message to be pushed upon receiving the error
   */
  public EnvironmentException(String message) {
    super(message);
  }
}