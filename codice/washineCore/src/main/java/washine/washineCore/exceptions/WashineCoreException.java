package washine.washineCore.exceptions;


public class WashineCoreException extends Exception {

    private static final long serialVersionUID = 1L;

    public WashineCoreException(String message) {
        super(message);
    }

    public WashineCoreException(Throwable cause) {
        super(cause);
    }

    public WashineCoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
