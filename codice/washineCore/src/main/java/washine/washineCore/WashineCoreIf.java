package washine.washineCore;

import java.sql.SQLException;

import washine.washineCore.user.WashineUserIf;

public interface WashineCoreIf {
  WashineUserIf authenticateUser(String email, String password) throws SQLException;

  boolean logOut();

  WashineUserIf addUser(String email, String password) throws SQLException;

  WashineUserIf updateUserEmail(String userId, String newEmail);

  WashineUserIf updateUserPassword(String userId, String newPassword);

  WashineUserIf blockUser(String adminId, String userId) throws SQLException;

  WashineUserIf unblockUser(String adminId, String userId) throws SQLException;

  WashineUserIf authenticateAdmin(String id) throws SQLException;
}
