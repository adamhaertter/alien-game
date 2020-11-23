package exceptions;

/**
 * @author Evan Paules
 *
 */
public class AttachmentException extends Exception {
  
  /**
   * Initializes a basic Attachment Exception for the program
   * 
   * @param message the message to be pushed upon receiving the error
   */
  public AttachmentException(String message) {
    super(message);
  }
}