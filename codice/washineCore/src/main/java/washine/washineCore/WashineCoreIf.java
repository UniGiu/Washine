package washine.washineCore;

import java.sql.SQLException;

import washine.washineCore.user.WashineUserIf;

public interface WashineCoreIf {
  WashineUserIf autenticateUser(String email, String password) throws SQLException;

  boolean logOut();

  WashineUserIf addUser(String email, String password) throws SQLException;

  WashineUserIf updateUserEmail(String userId, String newEmail) throws SQLException;

  WashineUserIf updateUserPassword(String userId, String newPassword) throws SQLException;
}
