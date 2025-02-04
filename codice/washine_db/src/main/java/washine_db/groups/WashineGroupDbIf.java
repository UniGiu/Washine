package washine_db.groups;


import java.util.List;

import org.jooq.Result;

import washine_db.exceptions.WashineDataException;
import washine_db.jooq.generated.tables.records.InvitesRecord;

/** Interface for the interaction between the community/invitations part of the database */
public interface WashineGroupDbIf {
  public boolean createGroup(String userId, String groupName) throws WashineDataException;

  public boolean addParticipationToGroup(
      String laundryPersonId, String participantId, String participantName, String communityName)
      throws WashineDataException;

  public void addInvite(String laundryPersonId, String invitedName, String code, int timestamp)
      throws WashineDataException;

  public Result<InvitesRecord> getInvites(String laundryPersonId) throws WashineDataException;

  public boolean removeInvite(String code) throws WashineDataException;

  public List<String> getGroupParticipants(String laundryPersonId) throws WashineDataException;

  public List<String> getParticipatedGroups(String participantId) throws WashineDataException;

  public boolean removeGroupMember(String participantId) throws WashineDataException;

  public boolean alreadyInvited(String laundryPersonId, String invitedName) throws WashineDataException;

  public boolean alreadyAdded(String laundryPersonId, String participantId) throws WashineDataException;

  public boolean updateCode(String invitedName, String newCode) throws WashineDataException;

  public boolean nameInCommunity(String name, String communityUid) throws WashineDataException;

  public String getLaundryPersonId(String code) throws WashineDataException;

  public String getInvitationName(String code) throws WashineDataException;

  public boolean clearExpiredInvitations(Integer timeToLive) throws WashineDataException;

  public boolean nameInJoinedCommunities(String name, String userId) throws WashineDataException;

  public boolean nameInInvitations(String name, String communityId) throws WashineDataException;
  
  public List<String> getCommunityMemberIds(String communityId) throws SQLException;
  
  public List<String> getCommunityMemberNames(String communityId) throws SQLException;

}
