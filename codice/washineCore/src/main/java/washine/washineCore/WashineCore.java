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
      String id = "1000";
      return new WashineUser(email, id);
    }
    return null;
  }

  @Override
  public WashineUserIf addUser(String email, String password) throws SQLException {
    // crea il gestore utenti
    WashineUserDb userDb = new WashineUserDb();
    String id = "1000";
    if (userDb.addUser(id, email, password)) {
      return new WashineUser(email, id);
    }
    return null;
  }

  @Override
  public boolean logOut() {
    // maybe it'sjust in the session
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
