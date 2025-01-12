package washine.washineCore;

import washine.washineCore.user.WashineUserIf;
import washine.washineCore.user.WashineUser;

public class WashineCore implements WashineCoreIf {

	@Override
	public WashineUserIf autenticateUser(String email,String password) {
		//crea il gestore utenti
		if(true) {
			String id="1000";
			return new WashineUser(email,id);
		}
		return null;
	}
	@Override
	public WashineUserIf addUser(String email,String password) {
		//crea il gestore utenti
		if(true) {
			String id="1000";
			return new WashineUser(email,id);
		}
		return null;
	}
	@Override
	public boolean logOut() {
		//maybe it'sjust in the session
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
