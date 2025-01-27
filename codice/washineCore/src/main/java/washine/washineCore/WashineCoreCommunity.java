package washine.washineCore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jooq.Result;

import washine.washineCore.exceptions.WashineCoreException;
import washine.washineCore.user.WashineUserIf;
import washine.washineCore.user.WashineUser;

import washine.washineCore.washing.WashineLaundryWashingIf;
import washine.washineCore.washing.WashineWashing;
import washine.washineCore.washing.WashineWashingOptions;

import java.sql.SQLException;
import java.time.Instant;

import washine_db.exceptions.WashineDataException;
import washine_db.groups.WashineGroupDb;
import washine_db.jooq.generated.tables.Washingoptions;
import washine_db.washings.WashineWashingDb;

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
    washineCommunity.clearExpiredInvitations(INVITATIONS_TIME_TO_LIVE);
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
        washineCommunity.addInvite(communityUid, name, code, (int) Instant.now().getEpochSecond());
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
    washineCommunity.clearExpiredInvitations(INVITATIONS_TIME_TO_LIVE);
    try {
      return washineCommunity.getLaundryPersonId(code);
    } catch (SQLException e) {
      return null;
    }
  }

  @Override
  public boolean joinCommunity(String uid, String invitationCode, String communityName) {
    WashineGroupDb washineCommunity = new WashineGroupDb();
    try {
      String communityUid = washineCommunity.getLaundryPersonId(invitationCode);
      if (communityUid.equals(uid)
          || communityUid == null
          || washineCommunity.alreadyAdded(communityUid, uid)) {
        return false;
      } else {
        washineCommunity.addParticipationToGroup(
            communityUid, uid, washineCommunity.getInvitationName(invitationCode), communityName);
        return washineCommunity.removeInvite(invitationCode);
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

  public String updateCode(String invitedName) {
    String newCode = generateUniqueCode();
    WashineGroupDb washineCommunity = new WashineGroupDb();
    try {
      if (washineCommunity.updateCode(invitedName, newCode)) {
        return newCode;
      }
    } catch (SQLException e) {
      return null;
    }
    return null;
  }

  public boolean nameInInvitations(String name, String communityId) {
    WashineGroupDb washineCommunity = new WashineGroupDb();
    return washineCommunity.nameInInvitations(name, communityId);
  }
  
  @Override
  public List<WashineUserIf> getCommunityMembers(String userId)
		  throws WashineCoreException, SQLException {
	  
      WashineGroupDb communityDb = new WashineGroupDb(); 
      List<WashineUserIf> communityMembers = new ArrayList<>();

      try {
    	  List<String> memberIds = communityDb.getCommunityMemberIds(userId); 

    	  for (String memberId : memberIds) {
	      
    		  WashineUser user = new WashineUser(null, memberId);
    		  String email = user.getEmail();
    		  WashineUser userFinal = new WashineUser(email, memberId); 
    		  communityMembers.add(userFinal); 
    	  }
    	  return communityMembers;
      } catch (SQLException e) {
          throw new WashineCoreException("Errore durante il recupero dei membri della community", e);
      }
  }
}
