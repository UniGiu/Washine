package washine_db.user;

import java.sql.SQLException;

import org.jooq.Result;

import washine_db.jooq.generated.tables.records.UserRecord;

/** Interface containing methods for the interaction with the database */
public interface WashineUserDbIf {
  public boolean authenticateUser(String email, String password) throws SQLException;

  public boolean addUser(String email, String password) throws SQLException;
  
  public boolean removeUserByEmail(String userId) throws SQLException;

  public boolean alreadyAddedUser(String email) throws SQLException;

  public String getUserId(String email) throws SQLException;

  public String getUserEmail(String id) throws SQLException;

  public void updateUserEmail(String userId, String newEmail) throws SQLException;

  public void updateUserPassword(String userId, String newPassword) throws SQLException;

  public void blockUser(String userId) throws SQLException;

  public void unblockUser(String userId) throws SQLException;

  public boolean isAdmin(String id) throws SQLException;

  public Result<UserRecord> getBlockedUsers() throws SQLException;
  
  public boolean isBlocked(String id) throws SQLException;
}
