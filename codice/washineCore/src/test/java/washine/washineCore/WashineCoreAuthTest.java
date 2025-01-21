package washine.washineCore;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class WashineCoreAuthTest {

  @Test
  void authenticateUserTest1() {
    WashineCoreAuth user = new WashineCoreAuth();
    assertNotNull(user.authenticateUser("partecipante1@a.com", "Prova123"));
  }

  @Test
  void authenticateUserTest2() {
    WashineCoreAuth user = new WashineCoreAuth();
    assertNull(user.authenticateUser("abc", "abc"));
  }

  @Test
  void addUserTest1() {
    WashineCoreAuth user = new WashineCoreAuth();
    assertNotNull(user.addUser("test3@gmail.com", "Test1234"));
  }

  @Test
  void addUserTest2() {
    WashineCoreAuth user = new WashineCoreAuth();
     assertNull(user.addUser("lavandaio@a.com", "Prova123"));
  }

  @Test
  void updateUserEmail() {
    WashineCoreAuth user = new WashineCoreAuth();
    assertNull(user.updateUserEmail("402717279044903536", "partecipante1@gmail.com"));
  }

  @Test
  void updateUserPassword() {
    WashineCoreAuth user = new WashineCoreAuth();
    assertNotNull(user.updateUserPassword("5006236931360928198", "Prova1234"));
  }

  @Test
  void authenticateAdminTest1() {
    WashineCoreAuth user = new WashineCoreAuth();
    assertNotNull(user.authenticateAdmin("0"));
  }

  @Test
  void authenticateAdminTest2() {
    WashineCoreAuth user = new WashineCoreAuth();
    assertNull(user.authenticateAdmin("8697783580247098624"));
  }

  @Test
  void blockUserTest1() {
    WashineCoreAuth user = new WashineCoreAuth();
    assertNull(user.blockUser("2713049981536184505", "0"));
  }

  @Test
  void blockUserTest2() {
    WashineCoreAuth user = new WashineCoreAuth();
    assertNotNull(user.blockUser("0", "2713049981536184505"));
  }

  @Test
  void unblockUserTest1() {
    WashineCoreAuth user = new WashineCoreAuth();
    assertNull(user.unblockUser("2713049981536184505", "0"));
  }

  @Test
  void unblockUserTest2() {
    WashineCoreAuth user = new WashineCoreAuth();
    assertNotNull(user.unblockUser("0", "2713049981536184505"));
  }
}
