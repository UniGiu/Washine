package washine.washineCore;

import java.util.Random;
import java.sql.SQLException;

import washine_db.groups.WashineGroupDb;

public class WashineCoreCommunity implements WashineCoreCommunityIf {

  static final int INVITATIONS_TIME_TO_LIVE = 1;

  @Override
  public boolean userInCommunity(String uid, String communityUid) {
    WashineGroupDb washineCommunity = new WashineGroupDb();
    try {
      return washineCommunity.alreadyAdded(communityUid, uid);
    } catch (SQLException e) {
      return false;
    }
  }

  @Override
  public boolean nameInCommunity(String name, String communityUid) {
    WashineGroupDb washineCommunity = new WashineGroupDb();
    try {
      return washineCommunity.nameInCommunity(name, communityUid);
    } catch (SQLException e) {
      return false;
    }
  }

  @Override
  public String getNewInvitationCode(String communityUid, String name) {
    String code = generateUniqueCode();
    WashineGroupDb washineCommunity = new WashineGroupDb();
    try {
      if (washineCommunity.alreadyInvited(communityUid, name)) {
        return null;
      } else {
        washineCommunity.addInvite(communityUid, name, code);
        return code;
      }
    } catch (SQLException e) {
      return null;
    }
  }

  @Override
  public boolean nameInJoinedCommunities(String name, String userId) {
    WashineGroupDb washineCommunity = new WashineGroupDb();
    return washineCommunity.nameInJoinedCommunities(name, userId);
  }

  @Override
  public String getInvitationCodeCommunityId(String code) {
    WashineGroupDb washineCommunity = new WashineGroupDb();
    try {
      return washineCommunity.getLaundryPersonId(code);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean joinCommunity(String uid, String invitationCode, String communityName) {
    WashineGroupDb washineCommunity = new WashineGroupDb();
    try {
      washineCommunity.clearExpiredInvitations(INVITATIONS_TIME_TO_LIVE);
      String communityUid = washineCommunity.getLaundryPersonId(invitationCode);
      if (communityUid.equals(uid)
          || communityUid == null
          || washineCommunity.alreadyAdded(communityUid, uid)) {
        return false;
      } else {
        return washineCommunity.addParticipationToGroup(
            communityUid, uid, washineCommunity.getInvitationName(invitationCode), communityName);
      }
    } catch (SQLException e) {
      return false;
    }
  }

  @Override
  public boolean removeUserFromCommunity(String uid, String communityUid) {
    WashineGroupDb washineCommunity = new WashineGroupDb();
    try {
      if (washineCommunity.alreadyAdded(communityUid, uid)) {
        return washineCommunity.removeGroupMember(uid);
      } else {
        return false;
      }
    } catch (SQLException e) {
      return false;
    }
  }

  public String generateUniqueCode() {
    String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuilder salt = new StringBuilder();
    Random rnd = new Random();
    while (salt.length() < 12) { // length of the random string.
      int index = (int) (rnd.nextFloat() * SALTCHARS.length());
      salt.append(SALTCHARS.charAt(index));
    }
    return salt.toString();
  }
}
