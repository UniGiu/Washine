package washine_db.groups;

import java.sql.SQLException;
import java.util.List;

import org.jooq.Result;

import washine_db.jooq.generated.tables.records.InvitesRecord;

public interface WashineGroupDbIf {
  public boolean createGroup(String userId, String groupName) throws SQLException;

  public boolean addParticipationToGroup(
      String laundryPersonId, String participantId, String participantName) throws SQLException;

  public void addInvite(String laundryPersonId, String invitedName, String code)
      throws SQLException;

  public Result<InvitesRecord> getInvites(String laundryPersonId) throws SQLException;

  public boolean removeInvite(String participantName) throws SQLException;

  public List<String> getGroupParticipants(String laundryPersonId) throws SQLException;

  public List<String> getParticipatedGroups(String participantId) throws SQLException;

  public boolean removeGroupMember(String participantId) throws SQLException;

  public boolean alreadyInvited(String laundryPersonId, String invitedName) throws SQLException;

  public boolean alreadyAdded(String laundryPersonId, String participantId) throws SQLException;

  public boolean updateCode(String invitedName, String newCode) throws SQLException;
}
