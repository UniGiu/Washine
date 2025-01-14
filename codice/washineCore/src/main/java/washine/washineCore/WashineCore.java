package washine.washineCore;

import washine.washineCore.user.WashineUserIf;
import washine_db.user.WashineUserDb;

import java.sql.SQLException;

import washine.washineCore.user.WashineUser;

public class WashineCore implements WashineCoreIf {

  @Override
  public WashineUserIf authenticateUser(String email, String password) {
    // crea il gestore utenti
    WashineUserDb userDb = new WashineUserDb();
    try {
      if (userDb.authenticateUser(email, password)) {
        String id = userDb.getUserId(email);
        return new WashineUser(email, id);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public WashineUserIf addUser(String email, String password) {
    // crea il gestore utenti
    WashineUserDb userDb = new WashineUserDb();
    try {
      if (!userDb.alreadyAddedUser(email) && userDb.addUser(email, password)) {
        String id = userDb.getUserId(email);
        return new WashineUser(email, id);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean logOut() {
    // maybe it's just relevant in the session
    return true;
  }

  @Override
  public WashineUserIf updateUserEmail(String userId, String newEmail) {
    WashineUserDb userDb = new WashineUserDb();
    try {
      if (!userDb.alreadyAddedUser(newEmail)) {
        userDb.updateUserEmail(userId, newEmail);
        if (userDb.getUserEmail(userId).equals(newEmail)) {
          return new WashineUser(newEmail, userId);
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public WashineUserIf updateUserPassword(String userId, String newPassword) {

    WashineUserDb userDb = new WashineUserDb();
    try {
      userDb.updateUserPassword(userId, newPassword);
      String email = userDb.getUserEmail(userId);
      // new login
      WashineUserIf userWithNewPassword = authenticateUser(email, newPassword);
      return userWithNewPassword;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public WashineUserIf blockUser(String adminId, String userId) {
    WashineUserDb userDb = new WashineUserDb();
    try {
      if (userDb.isAdmin(adminId)) {
        userDb.blockUser(userId);
        return new WashineUser(userDb.getUserEmail(userId), userId);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public WashineUserIf unblockUser(String adminId, String userId) {
    WashineUserDb userDb = new WashineUserDb();
    try {
      if (userDb.isAdmin(adminId)) {
        userDb.unblockUser(userId);
        return new WashineUser(userDb.getUserEmail(userId), userId);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public WashineUserIf authenticateAdmin(String id) {
    WashineUserDb userDb = new WashineUserDb();
    try {
      if (userDb.isAdmin(id)) {
        return new WashineUser(userDb.getUserEmail(id), id);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
