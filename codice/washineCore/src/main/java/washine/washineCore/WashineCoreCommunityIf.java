package washine.washineCore;

import java.sql.SQLException;

/** Community management interface */
public interface WashineCoreCommunityIf {
  /**
   * Checks if a user is part of a community
   *
   * @param uid id of the user to check
   * @param communityUid id of the user owning the community
   * @return true if the user is part of that community @
   */
  public boolean userInCommunity(String uid, String communityUid);

  /**
   * Checks if any user in a community is labelled by a certain name by the launder
   *
   * @param name name to check
   * @param communityUid id of the user owning the community
   * @return true if any user in that community is labelled by that name @
   */
  public boolean nameInCommunity(String name, String communityUid);

  /**
   * Creates a new invitation to a community.
   *
   * @param communityUid the id of the community to which the invitation is sent
   * @param name the name used to label the user who will join the community
   * @return an invitation code if the invitation was successfully created, or null if it was not
   *     possible to create the invitation @
   */
  public String getNewInvitationCode(String communityUid, String name);

  /**
   * Checks if any community of an user is labelled by a certain name
   *
   * @param name name to check
   * @param userId id of the user who joined the communities
   * @return true if any community joined by that user is labelled by that name @
   */
  public boolean nameInJoinedCommunities(String name, String userId);

  /**
   * Retrieves the community owner id given the invitation code
   *
   * @param code
   * @return community id
   */
  public String getInvitationCodeCommunityId(String code);

  /**
   * Confirms the invitation to a community by a user.
   *
   * @param uid the ID of the user confirming the invitation
   * @param invitationCode the invitation code provided by the invitee
   * @param communityName the name choosen to label the community
   * @return true if the join confirmation was successful, false otherwise @
   */
  public boolean joinCommunity(String uid, String invitationCode, String communityName);

  /**
   * Removes a user from a community.
   *
   * @param uid the ID of the user to be removed
   * @param communityUid the ID of the community
   * @return true if the user was successfully removed, false otherwise @
   */
  public boolean removeUserFromCommunity(String uid, String communityUid);
}
