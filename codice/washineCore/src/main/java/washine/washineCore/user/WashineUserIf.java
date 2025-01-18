package washine.washineCore.user;

/**
 * Authentication management interface
 */
public interface WashineUserIf {
    /**
     * Getds the email of the user.
     *
     * @return the email address of the user.
     */
    String getEmail();

    /**
     * Gets the uid of the user.
     *
     * @return the unique id of the user.
     */
    String getId();
}
