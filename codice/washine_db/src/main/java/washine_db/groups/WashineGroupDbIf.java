package washine_db.groups;

import java.sql.SQLException;
import java.util.List;

import org.jooq.Result;

import washine_db.jooq.generated.tables.records.InvitesRecord;

/** Interface for the interaction between the community/invitations part of the database */
public interface WashineGroupDbIf {
  public boolean createGroup(String userId, String groupName) throws SQLException;

  public boolean addParticipationToGroup(
      String laundryPersonId, String participantId, String participantName, String communityName)
      throws SQLException;

  public void addInvite(String laundryPersonId, String invitedName, String code, int timestamp)
      throws SQLException;

  public Result<InvitesRecord> getInvites(String laundryPersonId) throws SQLException;

  public boolean removeInvite(String code) throws SQLException;

  public List<String> getGroupParticipants(String laundryPersonId) throws SQLException;

  public List<String> getParticipatedGroups(String participantId) throws SQLException;

  public boolean removeGroupMember(String participantId) throws SQLException;

  public boolean alreadyInvited(String laundryPersonId, String invitedName) throws SQLException;

  public boolean alreadyAdded(String laundryPersonId, String participantId) throws SQLException;

  public boolean updateCode(String invitedName, String newCode) throws SQLException;

  public boolean nameInCommunity(String name, String communityUid) throws SQLException;

  public String getLaundryPersonId(String code) throws SQLException;

  public String getInvitationName(String code) throws SQLException;

  public boolean clearExpiredInvitations(Integer timeToLive);

  public boolean nameInJoinedCommunities(String name, String userId);

  public boolean nameInInvitations(String name, String communityId);
}
