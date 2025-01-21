package washine.washineCore;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class WashineCoreAuthTest {
	static WashineCoreAuth user;
	
	@BeforeAll
	@AfterAll
	static void setUp() {
		user = new WashineCoreAuth();		
		user.removeUserByEmail("test3@gmail.com");
	   
	}
	
	
  @Test
  void authenticateUserTest1() {   
    assertNotNull(user.authenticateUser("partecipante1@a.com", "Prova123"));
  }

  @Test
  void authenticateUserTest2() {  
    assertNull(user.authenticateUser("abc", "abc"));
  }

  @Test
  void addUserTest1() {   
    assertNotNull(user.addUser("test3@gmail.com", "Test1234"));
  }

  @Test
  void addUserTest2() {   
     assertNull(user.addUser("lavandaio@a.com", "Prova123"));
  }

  @Test
  void updateUserEmail() {   
    assertNull(user.updateUserEmail("402717279044903536", "partecipante1@gmail.com"));
  }

  @Test
  void updateUserPassword() {    
    assertNotNull(user.updateUserPassword("5006236931360928198", "Prova1234"));
  }

  @Test
  void authenticateAdminTest1() {   
    assertNotNull(user.authenticateAdmin("0"));
  }

  @Test
  void authenticateAdminTest2() {   
    assertNull(user.authenticateAdmin("8697783580247098624"));
  }

  @Test
  void blockUserTest1() {
    assertNull(user.blockUser("2713049981536184505", "0"));
  }

  @Test
  void blockUserTest2() {    
    assertNotNull(user.blockUser("0", "2713049981536184505"));
  }

  @Test
  void unblockUserTest1() {  
    assertNull(user.unblockUser("2713049981536184505", "0"));
  }

  @Test
  void unblockUserTest2() {    
    assertNotNull(user.unblockUser("0", "2713049981536184505"));
  }
  
}
