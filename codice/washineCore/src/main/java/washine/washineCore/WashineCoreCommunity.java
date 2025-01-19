package washine.washineCore;

import java.sql.SQLException;
import java.util.Random;

import washine_db.groups.WashineGroupDb;

public class WashineCoreCommunity implements WashineCoreCommunityIf {

  static final int INVITATIONS_TIME_TO_LIVE = 1;

  @Override
  public boolean userInCommunity(String uid, String communityUid) throws SQLException {
    WashineGroupDb washineCommunity = new WashineGroupDb();
    return washineCommunity.alreadyAdded(communityUid, uid);
  }

  @Override
  public boolean nameInCommunity(String name, String communityUid) throws SQLException {
    WashineGroupDb washineCommunity = new WashineGroupDb();
    return washineCommunity.nameInCommunity(name, communityUid);
  }

  @Override
  public String getNewInvitationCode(String communityUid, String name) throws SQLException {
    String code = generateUniqueCode();
    WashineGroupDb washineCommunity = new WashineGroupDb();
    if (washineCommunity.alreadyInvited(communityUid, name)) {
      return null;
    } else {
      washineCommunity.addInvite(communityUid, name, code);
      return code;
    }
  }

  @Override
  public boolean joinCommunity(String uid, String invitationCode) throws SQLException {
    WashineGroupDb washineCommunity = new WashineGroupDb();
    washineCommunity.clearExpiredInvitations(INVITATIONS_TIME_TO_LIVE);
    String communityUid = washineCommunity.getLaundryPersonId(invitationCode);
    if (communityUid == uid
        || communityUid == null
        || washineCommunity.alreadyAdded(communityUid, uid)) {
      return false;
    } else {
      return washineCommunity.addParticipationToGroup(
          communityUid, uid, washineCommunity.getInvitationName(invitationCode));
    }
  }

  @Override
  public boolean removeUserFromCommunity(String uid, String communityUid) throws SQLException {
    WashineGroupDb washineCommunity = new WashineGroupDb();

    if (washineCommunity.alreadyAdded(communityUid, uid)) {
      return washineCommunity.removeGroupMember(uid);
    } else {
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
