package washine.washineCore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import washine.washineCore.exceptions.WashineCoreException;

class WashineCoreCommunityTest {
  static WashineCoreCommunity user;

  @BeforeAll
  static void setUp() throws WashineCoreException {
    user =
        (WashineCoreCommunity)
            AbstractCoreFactory.getInstance("vaadin").createCoreWashineCommunity();
    user.getNewInvitationCode("5444046707115218308", "test-partecipante");
  }

  @Test
  @BeforeEach
  void addInviteTest1() {
    assertTrue(user.nameInInvitations("test-partecipante", "5444046707115218308"));
  }

  @Test
  void addInviteTest() {
    assertNull(user.getNewInvitationCode("5444046707115218308", "test-partecipante"));
  }

  @Test
  void nameInInvitationsTest() {
    assertFalse(user.nameInInvitations("test-sbagliato", "5444046707115218308"));
  }

  @Test
  void nameInCommunityTest2() {
    assertFalse(user.nameInCommunity("abc", "5444046707115218308"));
  }

  @Test
  void getIdThroughCodeTest1() {
    assertNull(user.getInvitationCodeCommunityId("a"));
  }

  @Test
  void updateCodeTest2() {
    assertNull(user.updateCode("test-sbagliato"));
  }

  @Test
  void generateUniqueCodeTest() {
    assertNotNull(user.generateUniqueCode());
  }

  @Test
  void joinCommunityTest1() {
    String code = user.updateCode("test-partecipante");
    assertFalse(user.joinCommunity("5444046707115218308", code, "nome"));
  }

  @Test
  void joinCommunityTest2() {
    String code = user.updateCode("test-partecipante");
    assertNotNull(code);
    assertNotNull(user.getInvitationCodeCommunityId(code));
    assertTrue(user.joinCommunity("2000", code, "test-nome-gruppo"));
    assertTrue(user.nameInJoinedCommunities("test-nome-gruppo", "2000"));
    assertTrue(user.nameInCommunity("test-partecipante", "5444046707115218308"));
    assertTrue(user.userInCommunity("2000", "5444046707115218308"));
    assertNotNull(user.getCommunityMemberId("5444046707115218308"));
  }

  @Test
  void nameInJoinedCommunitiesTest() {
    assertFalse(user.nameInJoinedCommunities("abc", "2000"));
  }

  @Test
  void userInCommunityTest() {
    assertFalse(user.userInCommunity("20001", "5444046707115218308"));
  }

  /*
   * Not running because getCommunityMembersIds returns the empty list and not null
    @Test
    void getCommunityMembersTest() {
      assert(user.getCommunityMembersIds("a"));
    }
  */
  @Test
  void removeUserTest1() {
    assertFalse(user.removeUserFromCommunity("a", "b"));
  }

  @AfterAll
  static void removeUser() {
    user.removeUserFromCommunity("2000", "5444046707115218308");
  }
}
