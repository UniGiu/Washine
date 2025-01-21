package washine.washineCore;

import washine.washineCore.user.WashineUserIf;
/**
 * Authentication management interface
 */
public interface WashineCoreAuthIf {
  
  /**
   * Authenticates a user with the provided email and password.
   *
   * @param email the email of the user
   * @param password the password of the user
   * @return the authenticated user, or null if authentication fails
   */
  WashineUserIf authenticateUser(String email, String password);

  /**
   * Logs out the currently authenticated user.
   *
   * @return true if the logout was successful, false otherwise
   */
  boolean logOut();

  /**
   * Adds a new user with the provided	 email and password.
   *
   * @param email the email of the new user
   * @param password the password of the new user
   * @return the newly created user
   */
  WashineUserIf addUser(String email, String password);

  /**
   * Updates the email of an existing user.
   *
   * @param userId the ID of the user to update
   * @param newEmail the new email to set
   * @return the updated user
   */
  WashineUserIf updateUserEmail(String userId, String newEmail);

  /**
   * Updates the password of an existing user.
   *
   * @param userId the ID of the user to update
   * @param newPassword the new password to set
   * @return the updated user
   */
  WashineUserIf updateUserPassword(String userId, String newPassword);

  /**
   * Blocks a user by the admin.
   *
   * @param adminId the ID of the admin performing the action
   * @param userId the ID of the user to block
   * @return the blocked user
   */
  WashineUserIf blockUser(String adminId, String userId);

  /**
   * Unblocks a user by the admin.
   *
   * @param adminId the ID of the admin performing the action
   * @param userId the ID of the user to unblock
   * @return the unblocked user
   */
  WashineUserIf unblockUser(String adminId, String userId);

  /**
   * Authenticates an admin with the provided ID.
   *
   * @param id the ID of the admin
   * @return the authenticated admin, or null if authentication fails
   */
  WashineUserIf authenticateAdmin(String id);
}
