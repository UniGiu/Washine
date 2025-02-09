package washine.washineCore;

import java.util.List;
import java.util.Random;

import washine.washineCore.exceptions.WashineCoreException;

import java.time.Instant;

import washine_db.exceptions.WashineDataException;
import washine_db.groups.WashineGroupDb;
import washine_db.groups.WashineGroupDbIf;
import washine_db.washings.WashineWashingDb;

public class WashineCoreCommunity implements WashineCoreCommunityIf {

  static final int INVITATIONS_TIME_TO_LIVE = 1;

  @Override
  public boolean userInCommunity(String uid, String communityUid) {
    WashineGroupDb washineCommunity = new WashineGroupDb();
    try {
      return washineCommunity.alreadyAdded(communityUid, uid);
    } catch (WashineDataException e) {
      return false;
    }
  }

  @Override
  public boolean nameInCommunity(String name, String communityUid) {
    WashineGroupDb washineCommunity = new WashineGroupDb();

    try {
      washineCommunity.clearExpiredInvitations(INVITATIONS_TIME_TO_LIVE);
      return washineCommunity.nameInCommunity(name, communityUid);
    } catch (WashineDataException e) {
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
    } catch (WashineDataException e) {
      return null;
    }
  }

  @Override
  public boolean nameInJoinedCommunities(String name, String userId) {
    WashineGroupDb washineCommunity = new WashineGroupDb();
    try {
      return washineCommunity.nameInJoinedCommunities(name, userId);
    } catch (WashineDataException e) {
      return false;
    }
  }

  @Override
  public String getInvitationCodeCommunityId(String code) {

    WashineGroupDb washineCommunity = new WashineGroupDb();

    try {
      washineCommunity.clearExpiredInvitations(INVITATIONS_TIME_TO_LIVE);
      return washineCommunity.getLaundryPersonId(code);
    } catch (WashineDataException e) {
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
    } catch (WashineDataException e) {
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
    } catch (WashineDataException e) {
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
    } catch (WashineDataException e) {
      return null;
    }
    return null;
  }

  public boolean nameInInvitations(String name, String communityId) {
    WashineGroupDb washineCommunity = new WashineGroupDb();
    try {
      return washineCommunity.nameInInvitations(name, communityId);
    } catch (WashineDataException e) {
      return false;
    }
  }

  @Override
  public List<String> getCommunityMemberId(String userId) {

    WashineGroupDb communityDb = new WashineGroupDb();

    try {
      return communityDb.getCommunityMemberIds(userId);

    } catch (WashineDataException e) {
      return null;
    }
  }

  @Override
  public List<String> getCommunityMemberName(String userId) {

    WashineGroupDb communityDb = new WashineGroupDb();

    try {
      return communityDb.getCommunityMemberNames(userId);

    } catch (WashineDataException e) {
      return null;
    }
  }
  public String getCommunityName(String communityId, String participantId )  throws WashineCoreException
  {
	  try {
		  WashineGroupDbIf groupDb=new WashineGroupDb();
		  List<String> communityNames=groupDb.getCommunityName(communityId,participantId);
		  if(communityNames.isEmpty()) {
			  throw new WashineCoreException("Community not found"+ " c id:"+communityId+" part id:"+participantId);
		  }
		  return communityNames.get(0);
		  
	  }catch (WashineDataException e) {
	      throw new WashineCoreException("Community does not exist"+ " c id:"+communityId+" part id:"+participantId);
	    }
  }
  public String getWashingCommunityName(String washingId, String participantId )  throws WashineCoreException
  {
	  try {
      WashineWashingDb washingDb = new WashineWashingDb();
		 
		  String communityId=washingDb.getWashingOwner(washingId);
		  if(communityId.isEmpty()) {
			  throw new WashineCoreException("Community Owner not found");
		  }
		  return getCommunityName(communityId,participantId);
		  
	  }catch (WashineDataException e) {
	      throw new WashineCoreException("Community does not exist");
	    }
  }
}
