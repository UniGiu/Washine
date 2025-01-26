package washine_db.groups;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import washine_db.jooq.generated.tables.Groups;
import washine_db.jooq.generated.tables.Communityuserslist;
import washine_db.jooq.generated.tables.Invites;
import washine_db.jooq.generated.tables.records.CommunityuserslistRecord;
import washine_db.jooq.generated.tables.records.InvitesRecord;
import washine_db.washine_db.JOOQCodeGeneration;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/** Group class used to interact with the database */
public class WashineGroupDb implements WashineGroupDbIf {
  private static Logger logger = LogManager.getLogger();

  public WashineGroupDb() {

    /* this constructor is empty */
  }
  ;

  /**
   * Creates a new group/community tuple in the database
   *
   * @throws SQLException
   * @param userId id of the group's creator
   * @param groupName name of the newly created group
   * @return true if only one tuple is added
   */
  public boolean createGroup(String userId, String groupName) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    int result = create.insertInto(Groups.GROUPS).values(userId, groupName).execute();

    return result == 1;
  }

  /**
   * Adds a participation to a group/community in the database
   *
   * @throws SQLException
   * @param laundryPersonId id of the group's creator
   * @param participantId id the person trying to join the group
   * @param participantName name of the participant which is given by the laundry person to
   *     recognize his group's participants
   * @return true if only one tuple is added
   */
  public boolean addParticipationToGroup(
      String laundryPersonId, String participantId, String participantName, String communityName)
      throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    int result =
        create
            .insertInto(Communityuserslist.COMMUNITYUSERSLIST)
            .values(laundryPersonId, participantId, participantName, communityName)
            .execute();

    return result == 1;
  }

  /**
   * Adds an invitation to a group/community in the database
   *
   * @throws SQLException
   * @param laundryPersonId id of the person who invited another person to join his group
   * @param invitedName name of the person invited (which is given by the laundry person to
   *     recognize) his group's participants
   * @param code the invitation's code
   */
  public void addInvite(String laundryPersonId, String invitedName, String code, int timestamp)
      throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    create
        .insertInto(
            Invites.INVITES,
            Invites.INVITES.LAUNDRYPERSONID,
            Invites.INVITES.INVITEDNAME,
            Invites.INVITES.CODE,
            Invites.INVITES.TS)
        .values(laundryPersonId, invitedName, code, timestamp)
        .execute();
  }

  /**
   * Removes an invitation to a group/community from the database through the participant name
   *
   * @throws SQLException
   * @param particiapantName name of the person whose invitation you want to revoke
   * @return true if only one tuple is removed
   */
  public boolean removeInvite(String code) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    int result = create.deleteFrom(Invites.INVITES).where(Invites.INVITES.CODE.eq(code)).execute();

    return result == 1;
  }

  /**
   * Selects the group's participant from the database, given the id of the person who created the
   * group
   *
   * @throws SQLException
   * @param laundryPersonId id of the group's creator
   * @return the list of participants' names
   */
  public List<String> getGroupParticipants(String laundryPersonId) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    return create
        .select()
        .from(Communityuserslist.COMMUNITYUSERSLIST)
        .where(Communityuserslist.COMMUNITYUSERSLIST.LAUNDRYPERSONID.eq(laundryPersonId))
        .fetch(Communityuserslist.COMMUNITYUSERSLIST.USERNAME);
  }

  /**
   * Selects the groups/communities that a person joined from the database
   *
   * @throws SQLException
   * @param participantId id of the person who joined the group/s
   * @return the list of groups'name
   */
  public List<String> getParticipatedGroups(String participantId) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    return create
        .select()
        .from(Groups.GROUPS)
        .join(Communityuserslist.COMMUNITYUSERSLIST)
        .on(Groups.GROUPS.USERID.eq(Communityuserslist.COMMUNITYUSERSLIST.LAUNDRYPERSONID))
        .where(Communityuserslist.COMMUNITYUSERSLIST.PARTICIPANTID.eq(participantId))
        .fetch(Groups.GROUPS.GROUPNAME);
  }

  /**
   * Selects the list of invitations to a group that a person has created from the database
   *
   * @throws SQLException
   * @param laundryPersonId id of the person who created the invitations
   * @return the records containing the invitations
   */
  public Result<InvitesRecord> getInvites(String laundryPersonId) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    return create
        .selectFrom(Invites.INVITES)
        .where(Invites.INVITES.LAUNDRYPERSONID.eq(laundryPersonId))
        .fetch();
    /*
     * to fetch the single tuple for(InvitesRecord r1 : result) { r1.g }
     */
  }

  /**
   * Removes a person from a given group through his id from the database
   *
   * @throws SQLException
   * @param participantId id of the person you want to remove from the group
   * @return true if only one tuple is removed
   */
  public boolean removeGroupMember(String participantId) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    int result =
        create
            .deleteFrom(Communityuserslist.COMMUNITYUSERSLIST)
            .where(Communityuserslist.COMMUNITYUSERSLIST.PARTICIPANTID.eq(participantId))
            .execute();

    return result == 1;
  }

  /**
   * Checks if an invite with a given name is already present in the database
   *
   * @throws SQLException
   * @param laundryPersonId id of the group's creator
   * @param invitedName name of the invited person
   * @return true if the person has already been invited or false if he has not been invited yet
   */
  public boolean alreadyInvited(String laundryPersonId, String invitedName) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    Result<InvitesRecord> invites =
        create
            .selectFrom(Invites.INVITES)
            .where(
                Invites.INVITES
                    .LAUNDRYPERSONID
                    .eq(laundryPersonId)
                    .and(Invites.INVITES.INVITEDNAME.eq(invitedName)))
            .fetch();
    return invites.isNotEmpty();
  }

  /**
   * Checks if a user has already been added to a group in the database by checking the group's
   * users list
   *
   * @throws SQLException
   * @param laundryPersonId id of the group's creator
   * @param participantId name of the user whose presence in the group you want to check
   * @return true if the person has already been added to the group or false if he has not been
   *     added yet
   */
  public boolean alreadyAdded(String laundryPersonId, String participantId) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    Result<CommunityuserslistRecord> usersList =
        create
            .selectFrom(Communityuserslist.COMMUNITYUSERSLIST)
            .where(
                Communityuserslist.COMMUNITYUSERSLIST
                    .LAUNDRYPERSONID
                    .eq(laundryPersonId)
                    .and(Communityuserslist.COMMUNITYUSERSLIST.PARTICIPANTID.eq(participantId)))
            .fetch();
    return !usersList.isEmpty();
  }

  /**
   * Updates the code of a group invitation in the database
   *
   * @throws SQLException
   * @param invitedName name of the person you want to change the invitation code of
   * @param newCode the new invitation code
   * @return true if only one tuple is added
   */
  public boolean updateCode(String invitedName, String newCode) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    int result =
        create
            .update(Invites.INVITES)
            .set(Invites.INVITES.CODE, newCode)
            .where(Invites.INVITES.INVITEDNAME.eq(invitedName))
            .execute();
    return result == 1;
  }

  /**
   * Selects a laundryPersonId (group's creator) given the unique invitation code that he created
   *
   * @throws SQLException
   * @param code code of the invitation
   * @return the laundry person id or null if no id correspond to the code
   */
  public String getLaundryPersonId(String code) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    Record1<String> id =
        create
            .select(Invites.INVITES.LAUNDRYPERSONID)
            .from(Invites.INVITES)
            .where(Invites.INVITES.CODE.eq(code))
            .fetchOne();
    if (id != null) {
      return id.getValue(Invites.INVITES.LAUNDRYPERSONID);
    }
    return null;
  }

  /**
   * Checks if a name is already present in the list of users of a community
   *
   * @throws SQLException
   * @param name to check
   * @param communityUid id of the community
   * @return true only if the name is already in the community
   */
  public boolean nameInCommunity(String name, String communityUid) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    Result<CommunityuserslistRecord> names =
        create
            .selectFrom(Communityuserslist.COMMUNITYUSERSLIST)
            .where(
                Communityuserslist.COMMUNITYUSERSLIST
                    .LAUNDRYPERSONID
                    .eq(communityUid)
                    .and(Communityuserslist.COMMUNITYUSERSLIST.USERNAME.eq(name)))
            .fetch();
    return names.isNotEmpty();
  }

  /**
   * Gets the invited name through the invitation code
   *
   * @throws SQLException
   * @param code code of the invitation
   * @return the name of the invited person if present
   */
  public String getInvitationName(String code) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    Record1<String> name =
        create
            .select(Invites.INVITES.INVITEDNAME)
            .from(Invites.INVITES)
            .where(Invites.INVITES.CODE.eq(code))
            .fetchOne();

    return name.getValue(Invites.INVITES.INVITEDNAME);
  }

  /**
   * Eliminates the expired invitations from the database
   *
   * @param timeToLive
   * @return true
   */
  public boolean clearExpiredInvitations(Integer timeToLive) {
    try {

      Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
      Integer secondsToLive = timeToLive * 60 * 60 * 24;
      logger.info("secondsToLive: " + secondsToLive);
      long unixTimestamp = Instant.now().getEpochSecond();
      Integer diff = (int) unixTimestamp - secondsToLive;
      logger.info("secondsToLive: " + secondsToLive);
      logger.info("unixTimestamp: " + unixTimestamp);
      logger.info("diff: " + diff);
      create.deleteFrom(Invites.INVITES).where(Invites.INVITES.TS.le(diff)).execute();
      return true;
    } catch (SQLException exc) {
      exc.printStackTrace();
    }
    return false;
  }

  /**
   * Checks if a name is already present in the list of users of a community
   *
   * @throws SQLException
   * @param name to check
   * @return true only if the name is already in the community
   */
  public boolean nameInJoinedCommunities(String name, String userId) {
    Connection conn;
    try {
      conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
      Result<CommunityuserslistRecord> communities =
          create
              .selectFrom(Communityuserslist.COMMUNITYUSERSLIST)
              .where(
                  Communityuserslist.COMMUNITYUSERSLIST
                      .PARTICIPANTID
                      .eq(userId)
                      .and(Communityuserslist.COMMUNITYUSERSLIST.COMMUNITYNAME.eq(name)))
              .fetch();
      return communities.isNotEmpty();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Checks if a name is already present in the invitations to a users of a community
   *
   * @throws SQLException
   * @param name to check
   * @param communityId id of the community
   * @return true only if the name is already in the community
   */
  public boolean nameInInvitations(String name, String communityId) {
    Connection conn;
    try {
      conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
      Result<InvitesRecord> invitations =
          create
              .selectFrom(Invites.INVITES)
              .where(
                  Invites.INVITES
                      .LAUNDRYPERSONID
                      .eq(communityId)
                      .and(Invites.INVITES.INVITEDNAME.eq(name)))
              .fetch();
      return invitations.isNotEmpty();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  

 public List<String> getCommunityMembersIds(String communityId) throws SQLException {
	    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
	    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

	    return create
	        .select(Communityuserslist.COMMUNITYUSERSLIST.PARTICIPANTID)
	        .from(Communityuserslist.COMMUNITYUSERSLIST)
	        .where(Communityuserslist.COMMUNITYUSERSLIST.LAUNDRYPERSONID.eq(communityId))
	        .fetchInto(String.class); 
	}

}
