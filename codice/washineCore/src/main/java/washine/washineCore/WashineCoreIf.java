package washine.washineCore;

import washine.washineCore.user.WashineUserIf;

public interface WashineCoreIf{
	WashineUserIf autenticateUser(String email,String password);
	boolean logOut();
	WashineUserIf addUser(String email,String password);
	WashineUserIf UpdateUserEmail (String newEmail);
	WashineUserIf UpdateUserPasswordl(String newPassword);
}
