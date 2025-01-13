package washine.washineCore;

import java.sql.SQLException;

import washine.washineCore.user.WashineUserIf;

public interface WashineCoreIf {
  WashineUserIf autenticateUser(String email, String password) throws SQLException;

  boolean logOut();

  WashineUserIf addUser(String email, String password) throws SQLException;

  WashineUserIf UpdateUserEmail(String newEmail);

  WashineUserIf UpdateUserPasswordl(String newPassword);
}
