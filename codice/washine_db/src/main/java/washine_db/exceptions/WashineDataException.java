package washine_db.exceptions;

public class WashineDataException extends Exception {

  /** */
  private static final long serialVersionUID = -5429389235586710163L;

  /**
   * to create {@code WashineDataException} with a detailed message
   *
   * @param s the message
   */
  public WashineDataException(String s) {
    super(s);
  }

  /**
   * to create {@code WashineDataException} with a cause
   *
   * @param cause the cause
   */
  public WashineDataException(Throwable cause) {
    super(cause);
  }

  /**
   * To create {@code WashineDataException} with a detailed message and a cause
   *
   * @param message the message
   * @param cause the cause
   */
  public WashineDataException(String message, Throwable cause) {
    super(message, cause);
  }
}
