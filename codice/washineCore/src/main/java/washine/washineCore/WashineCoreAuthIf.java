package washine.washineCore;

import java.sql.SQLException;

import washine.washineCore.user.WashineUserIf;

public interface WashineCoreAuthIf {
  WashineUserIf authenticateUser(String email, String password) throws SQLException;

  boolean logOut();

  WashineUserIf addUser(String email, String password);

  WashineUserIf updateUserEmail(String userId, String newEmail);

  WashineUserIf updateUserPassword(String userId, String newPassword);

  WashineUserIf blockUser(String adminId, String userId);

  WashineUserIf unblockUser(String adminId, String userId);

  WashineUserIf authenticateAdmin(String id);
}
