package washine.washineCore;

import washine.washineCore.user.WashineUserIf;
import washine_db.user.WashineUserDb;

import java.sql.SQLException;

import washine.washineCore.user.WashineUser;

public class WashineCore implements WashineCoreIf {

  @Override
  public WashineUserIf autenticateUser(String email, String password) throws SQLException {
    // crea il gestore utenti
    WashineUserDb userDb = new WashineUserDb();
    if (userDb.authenticateUser(email, password)) {
      String id = userDb.getUserId(email);
      return new WashineUser(email, id);
    }
    return null;
  }

  @Override
  public WashineUserIf addUser(String email, String password) throws SQLException {
    // crea il gestore utenti
    WashineUserDb userDb = new WashineUserDb();
    if (!userDb.alreadyAddedUser(email) && userDb.addUser(email, password)) {
      String id = userDb.getUserId(email);
      return new WashineUser(email, id);
    }
    return null;
  }

  @Override
  public boolean logOut() {
    // maybe it's just relevant in the session
    return true;
  }

  @Override
  public WashineUserIf updateUserEmail(String userId, String newEmail) throws SQLException {
    WashineUserDb userDb = new WashineUserDb();
    userDb.updateUserEmail(userId, newEmail);
    return new WashineUser(newEmail, userId);
  }

  @Override
  public WashineUserIf updateUserPassword(String userId, String newPassword) throws SQLException {
    WashineUserDb userDb = new WashineUserDb();
    userDb.updateUserPassword(userId, newPassword);
    return new WashineUser(userDb.getUserEmail(userId), userId);
  }
}
