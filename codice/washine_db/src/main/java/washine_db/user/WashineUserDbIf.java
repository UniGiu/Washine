package washine_db.user;

import org.jooq.Result;

import washine_db.exceptions.WashineDataException;
import washine_db.jooq.generated.tables.records.UserRecord;

/** Interface containing methods for the interaction with the database */
public interface WashineUserDbIf {
  public boolean authenticateUser(String email, String password) throws WashineDataException;

  public boolean addUser(String email, String password) throws WashineDataException;
  
  public boolean removeUserByEmail(String userId) throws WashineDataException;

  public boolean alreadyAddedUser(String email) throws WashineDataException;

  public String getUserId(String email) throws WashineDataException;

  public String getUserEmail(String id) throws WashineDataException;

  public void updateUserEmail(String userId, String newEmail) throws WashineDataException;

  public void updateUserPassword(String userId, String newPassword) throws WashineDataException;

  public void blockUser(String userId) throws WashineDataException;

  public void unblockUser(String userId) throws WashineDataException;

  public boolean isAdmin(String id) throws WashineDataException;

  public Result<UserRecord> getBlockedUsers() throws WashineDataException;
  
  public boolean isBlocked(String id) throws WashineDataException;
}
