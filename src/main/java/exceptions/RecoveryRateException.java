package exceptions;

public class RecoveryRateException extends Exception {

  /**
   * Initializes a basic Recovery Rate Exception for the program
   * 
   * @param message the message to be pushed upon receiving the error
   */
  public RecoveryRateException(String message) {
    super(message);
  }

}
