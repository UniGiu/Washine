package washine_db.groups;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.jooq.DSLContext;

import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import washine_db.jooq.generated.tables.Groups;
import washine_db.jooq.generated.tables.Groupuserslist;
import washine_db.jooq.generated.tables.Invites;
import washine_db.jooq.generated.tables.records.InvitesRecord;
import washine_db.washine_db.JOOQCodeGeneration;

/**
 * 
 */
public class WashineGroup implements WashineGroupIf {
  public WashineGroup() { /* TODO document why this constructor is empty */ }
  ;

  @Override
  public boolean createGroup(String userId, String groupName) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    int result = create.insertInto(Groups.GROUPS).values(userId, groupName).execute();

    return result == 1;
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

    return result == 1;
  }

  @Override
  public void addInvite(String laundryPersonId, String invitedName, String code)
      throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    create.insertInto(Invites.INVITES).values(laundryPersonId, invitedName, code, false).execute();
  }

  @Override
  public boolean checkInviteAndSetToAccepted(String invitedName, String code) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    int result =
        create
            .update(Invites.INVITES)
            .set(Invites.INVITES.ACCEPTED, true)
            .where(Invites.INVITES.INVITEDNAME.eq(invitedName).and(Invites.INVITES.CODE.eq(code)))
            .execute();

    return result == 1;
  }

  public boolean removeInvite(String participantName) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    int result =
        create
            .deleteFrom(Invites.INVITES)
            .where(Invites.INVITES.INVITEDNAME.eq(participantName))
            .execute();

    return result == 1;
  }

  @Override
  public List<String> getGroupParticipants(String laundryPersonId) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    return create
        .select()
        .from(Groupuserslist.GROUPUSERSLIST)
        .where(Groupuserslist.GROUPUSERSLIST.LAUNDRYPERSONID.eq(laundryPersonId))
        .fetch(Groupuserslist.GROUPUSERSLIST.NAME);
  }

  @Override
  public List<String> getParticipatedGroups(String participantId) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    return create
        .select()
        .from(Groups.GROUPS)
        .join(Groupuserslist.GROUPUSERSLIST)
        .on(Groups.GROUPS.USERID.eq(Groupuserslist.GROUPUSERSLIST.LAUNDRYPERSONID))
        .where(Groupuserslist.GROUPUSERSLIST.PARTICIPANTID.eq(participantId))
        .fetch(Groups.GROUPS.GROUPNAME);
  }

  public Result<InvitesRecord> getInvites(String laundryPersonId) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    return create
        .selectFrom(Invites.INVITES)
        .where(Invites.INVITES.LAUNDRYPERSONID.eq(laundryPersonId))
        .fetch();
    /*
     * to fetch the single tuple
    for(InvitesRecord r1 : result) {
    	r1.g
    }
    */
  }

  public boolean removeGroupMember(String participantId) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    int result =
        create
            .deleteFrom(Groupuserslist.GROUPUSERSLIST)
            .where(Groupuserslist.GROUPUSERSLIST.PARTICIPANTID.eq(participantId))
            .execute();

    return result == 1;
  }
}
