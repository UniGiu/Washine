package washine.washineCore;

import washine.washineCore.user.WashineUserIf;
import washine_db.exceptions.WashineDataException;
import washine_db.user.WashineUserDb;
import washine_db.user.WashineUserDbIf;
import washine.washineCore.exceptions.WashineCoreException;

import washine.washineCore.user.WashineUser;

public class WashineCoreAuth implements WashineCoreAuthIf {

  @Override
  public WashineUserIf authenticateUser(String email, String password) throws WashineCoreException {
    // crea il gestore utenti
    String lwcEmail=email.toLowerCase();
    WashineUserDbIf userDb = new WashineUserDb();
    try {
      if (userDb.authenticateUser(lwcEmail, password) && !userDb.isBlocked(userDb.getUserId(lwcEmail))) {
        String id = userDb.getUserId(lwcEmail);
        return new WashineUser(lwcEmail, id);
      }
    } catch (WashineDataException e) {
      throw new WashineCoreException("Core error authenticating user");
    }
    return null;
  }

  @Override
  public WashineUserIf addUser(String email, String password) throws WashineCoreException {
    // crea il gestore utenti
    String lwcEmail=email.toLowerCase();
    WashineUserDbIf userDb = new WashineUserDb();
    try {
      if (!userDb.alreadyAddedUser(lwcEmail) && userDb.addUser(lwcEmail, password)) {
        String id = userDb.getUserId(lwcEmail);
        return new WashineUser(lwcEmail, id);
      }
    } catch (WashineDataException e) {
      throw new WashineCoreException("Core error adding user");
    }
    return null;
  }

  // Not already in interface - for test only
  public boolean removeUserByEmail(String email) throws WashineCoreException {
    String lwcEmail=email.toLowerCase();
    WashineUserDbIf userDb = new WashineUserDb();
    boolean result;
    try {
      result = userDb.removeUserByEmail(lwcEmail);
    } catch (WashineDataException e) {
      throw new WashineCoreException("Core error removing user by email");
    }
    return result;
  }

  @Override
  public boolean logOut() {
    // maybe it's just relevant in the session
    return true;
  }

  @Override
  public WashineUserIf updateUserEmail(String userId, String newEmail) throws WashineCoreException {
    WashineUserDbIf userDb = new WashineUserDb();
    String lwcEmail=newEmail.toLowerCase();
    try {
      if (!userDb.alreadyAddedUser(lwcEmail)) {
        userDb.updateUserEmail(userId, lwcEmail);
        if (userDb.getUserEmail(userId).equals(lwcEmail)) {
          return new WashineUser(lwcEmail, userId);
        }
      }

    } catch (WashineDataException e) {
      throw new WashineCoreException("Core error updating user email");
    }
    return null;
  }

  @Override
  public WashineUserIf updateUserPassword(String userId, String newPassword)
      throws WashineCoreException {

    WashineUserDbIf userDb = new WashineUserDb();
    try {
      userDb.updateUserPassword(userId, newPassword);
      String email = userDb.getUserEmail(userId);
      // new login
      return authenticateUser(email, newPassword);
    } catch (WashineDataException e) {
      throw new WashineCoreException("Core error updating user password");
    }
  }

  @Override
  public WashineUserIf blockUser(String adminId, String userId) throws WashineCoreException {
    WashineUserDbIf userDb = new WashineUserDb();
    try {
      if (userDb.isAdmin(adminId)) {
        userDb.blockUser(userId);
        return new WashineUser(userDb.getUserEmail(userId), userId);
      }
    } catch (WashineDataException e) {
      throw new WashineCoreException("Core error blocking user");
    }
    return null;
  }

  @Override
  public WashineUserIf unblockUser(String adminId, String userId) throws WashineCoreException {
    WashineUserDbIf userDb = new WashineUserDb();
    try {
      if (userDb.isAdmin(adminId)) {
        userDb.unblockUser(userId);
        return new WashineUser(userDb.getUserEmail(userId), userId);
      }
    } catch (WashineDataException e) {
      throw new WashineCoreException("Core error unblocking user");
    }
    return null;
  }

  @Override
  public WashineUserIf authenticateAdmin(String id) throws WashineCoreException {
    WashineUserDbIf userDb = new WashineUserDb();
    try {
      if (userDb.isAdmin(id)) {
        return new WashineUser(userDb.getUserEmail(id), id);
      }
    } catch (WashineDataException e) {
      throw new WashineCoreException("Core error authenticating admin");
    }
    return null;
  }
}
