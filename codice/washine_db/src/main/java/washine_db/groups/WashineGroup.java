package washine_db.groups;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import washine_db.jooq.generated.tables.Groups;
import washine_db.jooq.generated.tables.Groupuserslist;
import washine_db.jooq.generated.tables.Invites;
import washine_db.washine_db.JOOQCodeGeneration;

public class WashineGroup implements WashineGroupIf {

  @Override
  public boolean createGroup(String userId, String groupName) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    int result = create.insertInto(Groups.GROUPS).values(userId, groupName).execute();
    if (result != 1) {
      return false;
    }
    return true;
  }

  @Override
  public boolean addParticipationToGroup(
      String laundryPersonId, String participantId, String participantName) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    int result =
        create
            .insertInto(Groupuserslist.GROUPUSERSLIST)
            .values(laundryPersonId, participantId, participantName)
            .execute();
    if (result != 1) {
      return false;
    }
    return true;
  }

  @Override
  public String getParticipationName(String laundryPersonId, String participantId)
      throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addInvite(String laundryPersonId, String invitedName, String code)
      throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    create.insertInto(Invites.INVITES).values(laundryPersonId, invitedName, code, false).execute();
  }

  @Override
  public boolean setInviteToAccepted(String invitedName, String code) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    int result =
        create
            .update(Invites.INVITES)
            .set(Invites.INVITES.ACCEPTED, true)
            .where(Invites.INVITES.INVITEDNAME.eq(invitedName))
            .execute();
    if (result != 1) {
      return false;
    }
    return true;
  }

  @Override
  public List<String> getGroupParticipants(String laundryPersonId, String groupName)
      throws SQLException {
	  
    return null;
  }

  @Override
  public List<String> getParticipatedGroups(String participantId) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }
}
