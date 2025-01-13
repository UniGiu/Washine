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
  public WashineUserIf UpdateUserEmail(String newEmail) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public WashineUserIf UpdateUserPasswordl(String newPassword) {
    // TODO Auto-generated method stub
    return null;
  }
}
