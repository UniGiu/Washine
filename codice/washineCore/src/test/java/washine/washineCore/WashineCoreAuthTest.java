package washine.washineCore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import washine.washineCore.exceptions.WashineCoreException;

class WashineCoreAuthTest {
	static WashineCoreAuth user;

	@BeforeAll
	@AfterAll
	static void setUp() throws WashineCoreException {
		user = new WashineCoreAuth();
		user.removeUserByEmail("test3@gmail.com");

	}

	@Test
	void authenticateUserTest1() throws WashineCoreException {
		assertNotNull(user.authenticateUser("partecipante1@a.com", "Prova123"));
	}

	@Test
	void authenticateUserTest2() throws WashineCoreException {
		assertNull(user.authenticateUser("abc", "abc"));
	}

	@Test
	void addUserTest1() throws WashineCoreException {
		assertNotNull(user.addUser("test3@gmail.com", "Test1234"));
	}

	@Test
	void addUserTest2() throws WashineCoreException {
		assertNull(user.addUser("lavandaio@a.com", "Prova123"));
	}
/*
	@Test
	void addUserException() {
		Throwable exception = assertThrows(WashineCoreException.class, () -> user.addUser(null, "Prova1234"));
	    assertEquals("Core error adding user", exception.getMessage());
	}
*/
	@Test
	void updateUserEmail() throws WashineCoreException {
		assertNull(user.updateUserEmail("402717279044903536", "partecipante1@gmail.com"));
	}
/*
	@Test
	void updateUserEmailException() {		
		Throwable exception = assertThrows(WashineCoreException.class, () -> user.updateUserEmail("NonEsistoId", "nessuna@email.exist"));
	    assertEquals("Core error updating user email", exception.getMessage());
	}
*/
	@Test
	void updateUserPassword() throws WashineCoreException {
		assertNotNull(user.updateUserPassword("5006236931360928198", "Prova1234"));
	}
/*
	@Test
	void updateUserPasswordException() {		
		Throwable exception = assertThrows(WashineCoreException.class, () -> user.updateUserPassword("NonEsistoId", "password"));
	    assertEquals("Core error updating user password", exception.getMessage());
	}
*/
	@Test
	void authenticateAdminTest1() throws WashineCoreException {
		assertNotNull(user.authenticateAdmin("0"));
	}

	@Test
	void authenticateAdminTest2() throws WashineCoreException {
		assertNull(user.authenticateAdmin("8697783580247098624"));
	}

	@Test
	void blockUserTest1() throws WashineCoreException {
		assertNull(user.blockUser("2713049981536184505", "0"));
	}

	@Test
	void blockUserTest2() throws WashineCoreException {
		assertNotNull(user.blockUser("0", "2713049981536184505"));
	}

	@Test
	void unblockUserTest1() throws WashineCoreException {
		assertNull(user.unblockUser("2713049981536184505", "0"));
	}

	@Test
	void unblockUserTest2() throws WashineCoreException {
		assertNotNull(user.unblockUser("0", "2713049981536184505"));
	}
}
