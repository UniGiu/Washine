package washine_db.user;

import java.sql.SQLException;

/** Interface containing methods for the interaction with the database */
public interface WashineUserDbIf {
  public boolean authenticateUser(String email, String password) throws SQLException;

  public boolean addUser(String email, String password) throws SQLException;

  public boolean alreadyAddedUser(String email) throws SQLException;

  public String getUserId(String email) throws SQLException;

  public String getUserEmail(String id) throws SQLException;

  public void updateUserEmail(String userId, String newEmail) throws SQLException;

  public void updateUserPassword(String userId, String newPassword) throws SQLException;
}
