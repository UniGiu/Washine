package washine_db.groups;

import java.sql.SQLException;
import java.util.List;

public interface WashineGroupIf {
  public boolean createGroup(String userId, String groupName) throws SQLException;

  public boolean addParticipationToGroup(
      String laundryPersonId, String participantId, String participantName) throws SQLException;

  public String getParticipationName(String laundryPersonId, String participantId)
      throws SQLException;

  public void addInvite(String laundryPersonId, String invitedName, String code)
      throws SQLException;

  public boolean setInviteToAccepted(String invitedName, String code) throws SQLException;

  public List<String> getGroupParticipants(String laundryPersonId, String groupName)
      throws SQLException;

  public List<String> getParticipatedGroups(String participantId) throws SQLException;
}
