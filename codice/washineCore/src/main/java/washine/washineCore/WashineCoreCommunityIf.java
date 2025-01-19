package washine.washineCore;

import java.sql.SQLException;

/** Community management interface */
public interface WashineCoreCommunityIf {
  /**
   * Checks if a user is part of a community
   *
   * @param uid id of the user to check
   * @param communityUid id of the user owning the community
   * @return true if the user is part of that community
   * @throws SQLException
   */
  public boolean userInCommunity(String uid, String communityUid) throws SQLException;

  /**
   * Checks if any user in a community is labelled by a certain name
   *
   * @param name name to check
   * @param communityUid id of the user owning the community
   * @return true if any user in that community is labelled by that name
   * @throws SQLException
   */
  public boolean nameInCommunity(String name, String communityUid) throws SQLException;

  /**
   * Creates a new invitation to a community.
   *
   * @param communityUid the id of the community to which the invitation is sent
   * @param name the name used to label the user who will join the community
   * @return an invitation code if the invitation was successfully created, or null if it was not
   *     possible to create the invitation
   * @throws SQLException
   */
  public String getNewInvitationCode(String communityUid, String name) throws SQLException;

  /**
   * Confirms the invitation to a community by a user.
   *
   * @param uid the ID of the user confirming the invitation
   * @param invitationCode the invitation code provided by the invitee
   * @return true if the join confirmation was successful, false otherwise
   * @throws SQLException
   */
  public boolean joinCommunity(String uid, String invitationCode) throws SQLException;

  /**
   * Removes a user from a community.
   *
   * @param uid the ID of the user to be removed
   * @param communityUid the ID of the community
   * @return true if the user was successfully removed, false otherwise
   * @throws SQLException
   */
  public boolean removeUserFromCommunity(String uid, String communityUid) throws SQLException;
}
