package washine_db.exceptions;

public class WashineDataException extends Exception {

  /** */
  private static final long serialVersionUID = -5429389235586710163L;

  /**
   * Costruttore di {@code WashineDataException} con messaggio di dettaglio
   *
   * @param s il messaggio di dettaglio
   */
  public WashineDataException(String s) {
    super(s);
  }

  /**
   * Costruttore di {@code WashineDataException} con causa
   *
   * @param cause la casa
   */
  public WashineDataException(Throwable cause) {
    super(cause);
  }

  /**
   * Costruttore di {@code WashineDataException} con messaggio di dettaglio e causa
   *
   * @param message il messaggio di dettaglio
   * @param cause la causa
   */
  public WashineDataException(String message, Throwable cause) {
    super(message, cause);
  }
}
